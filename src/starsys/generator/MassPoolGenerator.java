/*
 * Copyright (C) 2017 abudhabi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package starsys.generator;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import starsys.model.MassiveBody;
import starsys.model.OrbitalPoint;
import starsys.model.Star;
import starsys.util.Constants;
import starsys.util.SpectralClass;
import starsys.util.TerrestrialClass;

/**
 *
 * @author abudhabi
 */
public class MassPoolGenerator {
    private final Random random;
    private long currentId;

    public MassPoolGenerator(long seed) {
        this.random = new Random(seed);
        this.currentId = 0L;
    }
    
    OrbitalPoint generateStarSystem(Map<String,Object> params) {
        long id = currentId++;
        // Determine class and mass from the available mass given.
        double massPool;
        double starMass;
        SpectralClass primarySpectralClass = null;
        if (params != null && params.get("massPool") != null) {
            // Given a mass pool, decide what kind of star to make as primary.
            massPool = (double)params.get("massPool");
            for (SpectralClass sc: SpectralClass.values()) {
                if (sc.getUpperMass() <= massPool) {
                    primarySpectralClass = sc;
                }
            }
            if (primarySpectralClass == null) {
                throw new UnsupportedOperationException("Cannot create STAR system: Insufficient mass given. Rogue planets not supported yet!");
            } else {
                starMass = random.doubles(
                        primarySpectralClass.getLowerMass(), 
                        primarySpectralClass.getUpperMass())
                        .findFirst().getAsDouble();
            }
        } else {
            // No mass pool given, pick a random class and get a random star mass and system mass.
            primarySpectralClass = SpectralClass.getRandomSpectralClassOToM(random);
            starMass = random.doubles(
                    primarySpectralClass.getLowerMass(), 
                    primarySpectralClass.getUpperMass())
                    .findFirst().getAsDouble();
            massPool = (1.0+random.nextDouble()*0.05)*starMass;
        }
        // Tilt and rotation velocity aren't very relevant to stars.
        double tilt = 0;
        double rotationVelocity = 0;
        // Radius for main sequence is mass^0.8 (in solar units).
        // This is as good a ballpark as any.
        double massInSuns = starMass/Constants.SUN_MASS;
        double radiusInSuns = Math.pow(massInSuns,0.8);
        double radius = radiusInSuns*Constants.SUN_RADIUS;
        // Temperature can only be guessed at, without a model for stellar life.
        double temperature = random.doubles(
                    primarySpectralClass.getLowerTemperature(), 
                    primarySpectralClass.getUpperTemperature())
                    .findFirst().getAsDouble();
        // Albedo is theorized to be zero for these fusion candles.
        double albedo = 0;
        // Color assumed from spectral class. It's purely aesthetic.
        Color color = primarySpectralClass.getColor();
        // Name isn't final. Placeholder will do.
        String name = primarySpectralClass.name()+"-class Star ("+id+")";
        // Cached time can be safely set to zero, and center to (0,0).
        double cachedTime = 0;
        Point2D.Double center = new Point2D.Double();
        // Offset is random.
        int offset = random.nextInt();
        // This is a primary, so no parent. No children yet, but might as well instatiate the list.
        OrbitalPoint parent = null;
        List<OrbitalPoint> children = new ArrayList<>();
        // The semi-major axis as well as angular velocity is zero, due to being the primary.
        double semiMajorAxis = 0;
        double angularVelocity = 0;
        // Eccentricity and inclination are zero, pending any implementation of 3D movements.
        double eccentricity = 0;
        double inclination = 0;
        
        Star primary = new Star(primarySpectralClass, tilt, rotationVelocity, starMass, 
                        radius, temperature, albedo, color, id, name, cachedTime, 
                        offset, center, parent, children, semiMajorAxis, 
                        angularVelocity, eccentricity, inclination);
        
        // Fill out children.
        double remainingMass = massPool - starMass;
        populateStarWithSatellites(primary, remainingMass);
        
        return primary;
    }

    /**
     * @return the random
     */
    public Random getRandom() {
        return random;
    }

    private void populateStarWithSatellites(Star parent, double remainingMass) {
        // Is there enough mass remaining to support a companion star?
        SpectralClass aSuitableClass = null;
        for (SpectralClass sc: SpectralClass.getOToM()) {
            if (sc.getUpperMass() >= remainingMass) {
                aSuitableClass = sc;
            }
        }
        if (aSuitableClass != null) {
            // Let's make a companion.
            Star companion = createCompanionStar(aSuitableClass, parent);
            parent.addChildren(companion);
            remainingMass = remainingMass - companion.getMass();
            // Recursive call to this method. No need for special cases for various configurations.
            populateStarWithSatellites(companion, remainingMass);
        } else {
            // No companion stars possible!
            // Let's start with one of the closest semi-major axes observed, 
            // and average it out with Mercury's orbit. 
            double semiMajorAxis = Constants.KEPLER_70B_SEMIMAJOR_AXIS 
                                   + random.nextDouble()*Constants.MERCURY_SEMIMAJOR_AXIS;
            // Loop for planets and inner belts.
            // As long as there's a chance for a planet, keep going. 
            // Silicate is chosen arbitrarily. 
            // TODO: Refactor this.
            MassiveBody previous = null;
            while (remainingMass > TerrestrialClass.SILICATE.getLowerMass()) {
                MassiveBody body;
                if (random.nextBoolean()) {
                    // Gas giant. 
                    body = createGasGiantAndMaybeMoons(parent, semiMajorAxis);
                } else {
                    // Rocky. 
                    body = createTerrestrialAndMaybeMoons(parent, semiMajorAxis);
                }
                if (body.getMassOfEntireSubSystemFromThisNode() > remainingMass) {
                    // Mass exceeded. Proceed to outer belt generation; discard planet.
                    break;
                } else {
                    remainingMass = remainingMass - body.getMassOfEntireSubSystemFromThisNode();
                    parent.addChildren(body);
                }
                if (previous != null) {
                    double mutualHillRadius = mutualHillRadius(previous, body);
                    semiMajorAxis = semiMajorAxis + 10*mutualHillRadius + random.nextDouble()*20*mutualHillRadius;
                    body.setSemiMajorAxis(semiMajorAxis);
                    recalculateAngularVelocity(body); // SMA changes, speed changes.
                }
            }
            // Finisher - outer debris belt. Only for the primary!
            if (parent.getParent() == null) {
                createAndAddOuterBelt(parent, remainingMass);
            }
            
        }
        
    }

    private Star createCompanionStar(SpectralClass aSuitableClass, Star primary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private MassiveBody createGasGiantAndMaybeMoons(Star parent, double semiMajorAxis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private MassiveBody createTerrestrialAndMaybeMoons(Star parent, double semiMajorAxis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAndAddOuterBelt(Star parent, double remainingMass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double mutualHillRadius(MassiveBody previous, MassiveBody body) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void recalculateAngularVelocity(MassiveBody body) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

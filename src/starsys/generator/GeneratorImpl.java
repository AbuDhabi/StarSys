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

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.model.MassiveBody;
import starsys.model.OrbitalPoint;
import starsys.model.Star;
import starsys.model.Terrestrial;
import starsys.util.Constants;
import starsys.util.SpectralClass;

/**
 *
 * @author abudhabi
 */
public class GeneratorImpl implements Generator {
    private final Random random;
    private long currentId;

    public GeneratorImpl(Random r) {
        this.random = r;
        currentId = 0;
    }

    @Override
    public Star generateStar(CelestialBodyParameters params) {
        long id = currentId++;
        CelestialBodyParameters builder = new CelestialBodyParameters();
        if (params.getSpectralClass() == null) {
            builder.setSpectralClass(SpectralClass.getRandomSpectralClassOToM(random));
        } else {
            builder.setSpectralClass(params.getSpectralClass());
        }
        
        if (params.getName() == null) {
            builder.setName(builder.getSpectralClass().name()+"-class Star ("+id+")");
        } else {
            builder.setName(params.getName());
        }
        
        // Get random values from ranges in the spectral class definitions.
        builder.setTemperature(
                random.doubles(
                builder.getSpectralClass().getLowerTemperature(),
                builder.getSpectralClass().getUpperTemperature())
                .findFirst().getAsDouble()
        );
        builder.setMass(random.doubles(
                builder.getSpectralClass().getLowerMass(), 
                builder.getSpectralClass().getUpperMass())
                .findFirst().getAsDouble()
        );
        builder.setRadius(random.doubles(
                builder.getSpectralClass().getLowerRadius(), 
                builder.getSpectralClass().getUpperRadius())
                .findFirst().getAsDouble()
        );
        // Primary, or companion?
        Point2D.Double center; 
        double semiMajorAxis;
        double angularVelocity;
        if (params.getParent() == null) {
            center = new Point2D.Double(); // 0,0
            semiMajorAxis = 0;
            angularVelocity = 0;
            builder.setParent(null);
        } else {
            builder.setParent(params.getParent());
            center = null;
            double lowerBound;
            double upperBound;
            if (random.nextBoolean()) {
                // Near companion. Up to 100 AU from the primary.
                upperBound = 100 * Constants.ASTRONOMICAL_UNIT;
                if (params.getParent() instanceof MassiveBody) {
                    // Meaning it has a radius, which the child should stay away from.
                    lowerBound = 2* ((MassiveBody)params.getParent()).getRadius();
                } else {
                    lowerBound = Constants.ASTRONOMICAL_UNIT;
                }
            } else {
                // Far companion. More than 100 AU from the primary.
                lowerBound = Constants.ASTRONOMICAL_UNIT;
                upperBound = 1000*Constants.ASTRONOMICAL_UNIT;
            }
            semiMajorAxis = lowerBound + random.nextDouble()*(upperBound-lowerBound);
            // Many hours died to bring us this solution.
            double parentMass;
            if (params.getParent() instanceof MassiveBody) {
                parentMass = ((MassiveBody)params.getParent()).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(builder.getMass() + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(semiMajorAxis, 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        if (params.getCenter() == null) {
            builder.setCenter(center);
        } else {
            builder.setCenter(params.getCenter());
        }
        if (params.getSemiMajorAxis() == null) {
            builder.setSemiMajorAxis(semiMajorAxis);
        } else {
            builder.setSemiMajorAxis(params.getSemiMajorAxis());
        }
        if (params.getAngularVelocity() == null) {
            builder.setAngularVelocity(angularVelocity);
        } else {
            builder.setAngularVelocity(params.getAngularVelocity());
        }
        if (params.getEccentricity() == null) {
            builder.setEccentricity(0.0);
        } else {
            builder.setEccentricity(params.getEccentricity());
        }
        if (params.getInclination() == null) {
            builder.setInclination(0.0);
        } else {
            builder.setInclination(params.getInclination());
        }
        if (params.getTilt() == null) {
            builder.setTilt(0.0);
        } else {
            builder.setTilt(params.getTilt());
        }
        if (params.getRotationVelocity() == null) {
            builder.setRotationVelocity(0.0);
        } else {
            builder.setRotationVelocity(params.getRotationVelocity());
        }
        if (params.getAlbedo() == null) {
            builder.setAlbedo(0.0); // A star is probably a good blackbody.
        } else {
            builder.setAlbedo(params.getAlbedo());
        }
        if (params.getCachedTime() == null) {
            builder.setCachedTime(0.0);
        } else {
            builder.setCachedTime(params.getCachedTime());
        }
        if (params.getOffset()== null) {
            builder.setOffset(random.nextInt());
        } else {
            builder.setOffset(params.getOffset());
        }
        if (params.getChildren() == null) {
            builder.setChildren(new ArrayList<>());
        } else {
            builder.setChildren(params.getChildren());
        }
        
        return new Star(
                builder.getSpectralClass(),
                builder.getTilt(),
                builder.getRotationVelocity(),
                builder.getMass(),
                builder.getRadius(), 
                builder.getTemperature(),
                builder.getAlbedo(), 
                builder.getSpectralClass().getColor(),
                id,
                builder.getName(), 
                builder.getCachedTime(), 
                builder.getOffset(),
                builder.getCenter(), 
                builder.getParent(), 
                builder.getChildren(),
                builder.getSemiMajorAxis(),
                builder.getAngularVelocity(), 
                builder.getEccentricity(), 
                builder.getInclination());
    }

    @Override
    public Terrestrial generateTerrestrialWorld(CelestialBodyParameters params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chunk generateChunk(CelestialBodyParameters params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GasGiant generateGasGiant(CelestialBodyParameters params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Star generateStarSystem(SystemParameters params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MassiveBody populatePlanetWithMoons(SystemParameters params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

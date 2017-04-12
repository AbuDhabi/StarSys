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
import java.util.Random;
import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.model.MassiveBody;
import starsys.model.OrbitalPoint;
import starsys.model.SpectralClass;
import starsys.model.Star;
import starsys.model.Terrestrial;
import starsys.util.Constants;

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
    public Star generateRandomStar(SpectralClass spectralClass, OrbitalPoint parent) {
        // Get random values from ranges in the spectral class definitions.
        double temp = random.doubles(spectralClass.getLowerTemperature(), spectralClass.getUpperTemperature()).findFirst().getAsDouble();
        double mass = random.doubles(spectralClass.getLowerMass(), spectralClass.getUpperMass()).findFirst().getAsDouble();
        double radius = random.doubles(spectralClass.getLowerRadius(), spectralClass.getUpperRadius()).findFirst().getAsDouble();
        // Primary, or companion?
        Point2D.Double center; 
        double semiMajorAxis;
        double angularVelocity;
        if (parent == null) {
            center = new Point2D.Double(); // 0,0
            semiMajorAxis = 0;
            angularVelocity = 0;
        } else {
            center = null;
            double lowerBound;
            double upperBound;
            if (random.nextBoolean()) {
                // Near companion. Up to 100 AU from the primary.
                upperBound = 100 * Constants.ASTRONOMICAL_UNIT;
                if (parent instanceof MassiveBody) {
                    // Meaning it has a radius, which the child should stay away from.
                    lowerBound = 2* ((MassiveBody)parent).getRadius();
                } else {
                    lowerBound = Constants.ASTRONOMICAL_UNIT;
                }
            } else {
                // Far companion. More than 100 AU from the primary.
                lowerBound = Constants.ASTRONOMICAL_UNIT;
                upperBound = 1000*Constants.ASTRONOMICAL_UNIT;
            }
            semiMajorAxis = lowerBound + random.nextDouble()*(upperBound-lowerBound);
            // TODO: Figure out angular velocity on companions. There's a formula!
            double parentMass;
            if (parent instanceof MassiveBody) {
                parentMass = ((MassiveBody)parent).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(mass + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(semiMajorAxis, 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        double ecce = 0;
        double incl = 0;
        double tilt = 0;
        double rota = 0;
        double albedo = 0; // A star is probably a good blackbody.
        
        return new Star(
                spectralClass,
                tilt,
                rota,
                mass,
                radius, 
                temp,
                albedo, 
                spectralClass.getColor(),
                currentId++,
                null, 
                0L, 
                random.nextInt(),
                center, 
                parent, 
                null,
                semiMajorAxis,
                angularVelocity, 
                ecce, 
                incl);
    }

    @Override
    public Terrestrial generateRandomTerrestrialWorld(OrbitalPoint parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chunk generateRandomChunk(OrbitalPoint parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GasGiant generateRandomGasGiant(OrbitalPoint parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Star generateRandomStarSystem(int nrofSuns, String spectralClassOfPrimary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MassiveBody populatePlanetWithMoons(int nrofMoons, MassiveBody parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

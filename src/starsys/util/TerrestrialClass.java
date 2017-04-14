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
package starsys.util;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author abudhabi
 */
public enum TerrestrialClass {
    SILICATE ("Silicate", 3.5*10e13/10e3, 6.0*10e13/10e3, 6e20, 6*Constants.EARTH_MASS, Color.ORANGE),
    IRON     ("Iron",     5.0*10e13/10e3, 6.5*10e13/10e3, 6e20, 6*Constants.EARTH_MASS, Color.ORANGE),
    CORELESS ("Coreless", 3.5*10e13/10e3, 5.0*10e13/10e3, 6e20, 6*Constants.EARTH_MASS, Color.ORANGE),
    CARBON   ("Carbon",   2.0*10e13/10e3, 4.0*10e13/10e3, 6e20, 6*Constants.EARTH_MASS, Color.ORANGE),
    ICE      ("Icy",      1.0*10e13/10e3, 2.5*10e13/10e3, 6e20, 6*Constants.EARTH_MASS, Color.ORANGE);
    
    private final String adjective;
    // In kilograms per cubic kilometer.
    private final double lowerDensity;
    // In kilograms per cubic kilometer.
    private final double upperDensity;
    private final double lowerMass;
    private final double upperMass;
    private final Color color;

    private TerrestrialClass(String adjective, double lowerDensity, double upperDensity, double lowerMass, double upperMass, Color color) {
        this.adjective = adjective;
        this.lowerDensity = lowerDensity;
        this.upperDensity = upperDensity;
        this.lowerMass = lowerMass;
        this.upperMass = upperMass;
        this.color = color;
    }
    
    public static TerrestrialClass getRandomTerrestrialClass(Random random) {
        int number = random.nextInt(80);
        if (number <= 20) {
            return SILICATE;
        } else if (number > 20  && number <= 40) {
            return IRON;
        } else if (number > 40  && number <= 45) {
            return CORELESS;
        } else if (number > 45  && number <= 50) {
            return CARBON;
        } else if (number > 50) {
            return ICE;
        }
        return null; // Guess the random number generator is malfunctioning.
    }

    /**
     * @return the lowerDensity
     */
    public double getLowerDensity() {
        return lowerDensity;
    }

    /**
     * @return the upperDensity
     */
    public double getUpperDensity() {
        return upperDensity;
    }

    /**
     * @return the lowerMass
     */
    public double getLowerMass() {
        return lowerMass;
    }

    /**
     * @return the upperMass
     */
    public double getUpperMass() {
        return upperMass;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the adjective
     */
    public String getAdjective() {
        return adjective;
    }
    

}

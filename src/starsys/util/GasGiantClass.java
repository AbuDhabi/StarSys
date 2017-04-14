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
public enum GasGiantClass {
    I   (10*Constants.EARTH_MASS, 500*Constants.EARTH_MASS, 0.5, 2.0, 0,    150,              Color.ORANGE),
    II  (10*Constants.EARTH_MASS, 500*Constants.EARTH_MASS, 0.5, 2.0, 150,  250,              Color.GRAY),
    III (10*Constants.EARTH_MASS, 500*Constants.EARTH_MASS, 0.5, 2.0, 350,  800,              Color.BLUE),
    IV  (10*Constants.EARTH_MASS, 500*Constants.EARTH_MASS, 0.5, 2.0, 900,  1400,             Color.DARK_GRAY),
    V   (10*Constants.EARTH_MASS, 500*Constants.EARTH_MASS, 0.5, 2.0, 1400, Double.MAX_VALUE, Color.GREEN);

    public static GasGiantClass getRandomGasGiantClass(Random random) {
        int number = random.nextInt(5);
        if (number == 0) return I;
        if (number == 1) return II;
        if (number == 2) return III;
        if (number == 3) return IV;
        if (number == 4) return V;
        return null; // RNG malfunction alert!
    }
    
    private final double lowerMass;
    private final double upperMass;
    private final double lowerDensity;
    private final double upperDensity;
    private final double lowerTemperature;
    private final double upperTemperature;
    private final Color color;

    private GasGiantClass(double lowerMass, double upperMass, double lowerDensity, double upperDensity, double lowerTemperature, double upperTemperature, Color color) {
        this.lowerMass = lowerMass;
        this.upperMass = upperMass;
        this.lowerDensity = lowerDensity;
        this.upperDensity = upperDensity;
        this.lowerTemperature = lowerTemperature;
        this.upperTemperature = upperTemperature;
        this.color = color;
    }


    /**
     * @return the lowerTemperature
     */
    public double getLowerTemperature() {
        return lowerTemperature;
    }

    /**
     * @return the upperTemperature
     */
    public double getUpperTemperature() {
        return upperTemperature;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
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
    
    
}

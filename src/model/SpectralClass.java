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
package model;

import java.awt.Color;

/**
 *
 * @author abudhabi
 */
public enum SpectralClass {
    O (60000, 30000,   150*Sun.mass,    18*Sun.mass,   10*Sun.radius,   6.6*Sun.radius, Color.CYAN),
    B (30000, 10000,    18*Sun.mass,   2.9*Sun.mass,  6.6*Sun.radius,   1.8*Sun.radius, Color.CYAN),
    A (10000,  7300,   2.9*Sun.mass,   1.6*Sun.mass,  1.8*Sun.radius,   1.4*Sun.radius, Color.BLUE),
    F (7300,   6000,   1.6*Sun.mass,  1.05*Sun.mass,  1.4*Sun.radius,  1.15*Sun.radius, Color.WHITE),
    G (6000,   5300,  1.05*Sun.mass,   0.8*Sun.mass, 1.15*Sun.radius,  0.96*Sun.radius, Color.YELLOW),
    K (5300,   3800,   0.8*Sun.mass,   0.5*Sun.mass, 0.96*Sun.radius,   0.7*Sun.radius, Color.ORANGE),
    M (3800,   2500,   0.5*Sun.mass,  0.07*Sun.mass,  0.7*Sun.radius,   0.2*Sun.radius, Color.RED),
    C (3200,   2400,   1.1*Sun.mass,   0.5*Sun.mass,  550*Sun.radius,   220*Sun.radius, Color.RED),
    S (3500,   2400,   0.8*Sun.mass,   0.5*Sun.mass,  0.7*Sun.radius,   0.2*Sun.radius, Color.RED),
    L (2000,   1300,  0.45*Sun.mass, 0.075*Sun.mass,  0.2*Sun.radius,   0.1*Sun.radius, Color.RED),
    T (1300,    700, 0.075*Sun.mass, 0.012*Sun.mass,  0.2*Sun.radius,   0.1*Sun.radius, Color.MAGENTA),
    D (100000, 5000,   1.3*Sun.mass,  0.17*Sun.mass, 0.02*Sun.radius, 0.008*Sun.radius, Color.MAGENTA);
    
    private final double upperTemperature;
    private final double lowerTemperature;
    private final double upperMass;
    private final double lowerMass;
    private final double upperRadius;
    private final double lowerRadius;
    private final Color color;
    

    private SpectralClass(double upperTemperature, double lowerTemperature, double upperMass, double lowerMass, double upperRadius, double lowerRadius, Color color) {
        this.upperTemperature = upperTemperature;
        this.lowerTemperature = lowerTemperature;
        this.upperMass = upperMass;
        this.lowerMass = lowerMass;
        this.upperRadius = upperRadius;
        this.lowerRadius = lowerRadius;
        this.color = color;
    }

    /**
     * @return the upperTemperature
     */
    public double getUpperTemperature() {
        return upperTemperature;
    }

    /**
     * @return the lowerTemperature
     */
    public double getLowerTemperature() {
        return lowerTemperature;
    }

    /**
     * @return the upperMass
     */
    public double getUpperMass() {
        return upperMass;
    }

    /**
     * @return the lowerMass
     */
    public double getLowerMass() {
        return lowerMass;
    }

    /**
     * @return the upperRadius
     */
    public double getUpperRadius() {
        return upperRadius;
    }

    /**
     * @return the lowerRadius
     */
    public double getLowerRadius() {
        return lowerRadius;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    
    private class Sun {
        private static final double mass = 1.98855e30;
        private static final double radius = 696342;
    }
    
}

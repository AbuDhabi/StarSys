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
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author abudhabi
 */
public enum SpectralClass {
    O (60000, 30000,   150*Constants.SUN_MASS,    18*Constants.SUN_MASS,   10*Constants.SUN_RADIUS,   6.6*Constants.SUN_RADIUS, Color.CYAN),
    B (30000, 10000,    18*Constants.SUN_MASS,   2.9*Constants.SUN_MASS,  6.6*Constants.SUN_RADIUS,   1.8*Constants.SUN_RADIUS, Color.CYAN),
    A (10000,  7300,   2.9*Constants.SUN_MASS,   1.6*Constants.SUN_MASS,  1.8*Constants.SUN_RADIUS,   1.4*Constants.SUN_RADIUS, Color.BLUE),
    F (7300,   6000,   1.6*Constants.SUN_MASS,  1.05*Constants.SUN_MASS,  1.4*Constants.SUN_RADIUS,  1.15*Constants.SUN_RADIUS, Color.WHITE),
    G (6000,   5300,  1.05*Constants.SUN_MASS,   0.8*Constants.SUN_MASS, 1.15*Constants.SUN_RADIUS,  0.96*Constants.SUN_RADIUS, Color.YELLOW),
    K (5300,   3800,   0.8*Constants.SUN_MASS,   0.5*Constants.SUN_MASS, 0.96*Constants.SUN_RADIUS,   0.7*Constants.SUN_RADIUS, Color.ORANGE),
    M (3800,   2500,   0.5*Constants.SUN_MASS,  0.07*Constants.SUN_MASS,  0.7*Constants.SUN_RADIUS,   0.2*Constants.SUN_RADIUS, Color.RED),
    C (3200,   2400,   1.1*Constants.SUN_MASS,   0.5*Constants.SUN_MASS,  550*Constants.SUN_RADIUS,   220*Constants.SUN_RADIUS, Color.RED),
    S (3500,   2400,   0.8*Constants.SUN_MASS,   0.5*Constants.SUN_MASS,  0.7*Constants.SUN_RADIUS,   0.2*Constants.SUN_RADIUS, Color.RED),
    L (2000,   1300,  0.45*Constants.SUN_MASS, 0.075*Constants.SUN_MASS,  0.2*Constants.SUN_RADIUS,   0.1*Constants.SUN_RADIUS, Color.RED),
    T (1300,    700, 0.075*Constants.SUN_MASS, 0.012*Constants.SUN_MASS,  0.2*Constants.SUN_RADIUS,   0.1*Constants.SUN_RADIUS, Color.MAGENTA),
    D (100000, 5000,   1.3*Constants.SUN_MASS,  0.17*Constants.SUN_MASS, 0.02*Constants.SUN_RADIUS, 0.008*Constants.SUN_RADIUS, Color.MAGENTA);

    public static SpectralClass getRandomSpectralClassOToM(Random random) {
        int number = random.nextInt(300);
        if (number == 0) {
            return O;
        } else if (number >= 1 && number < 20) {
            return B;
        } else if (number >= 20 && number < 50) {
            return A;
        } else if (number >= 50 && number < 90) {
            return F;
        } else if (number >= 90 && number < 160) {
            return G;
        } else if (number >= 160 && number < 220) {
            return K;
        } else if (number >= 220) {
            return M;
        }
        return null; // Guess the random number generator is malfunctioning.
    }
    
    public static SpectralClass getThisOrLighterClass(Random random, SpectralClass spectralClass) {
        if (spectralClass == O) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(O,B,A,F,G,K,M));
        } else if (spectralClass == B) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(B,A,F,G,K,M));
        } else if (spectralClass == A) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(A,F,G,K,M));
        } else if (spectralClass == F) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(F,G,K,M));
        } else if (spectralClass == G) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(G,K,M));
        } else if (spectralClass == K) {
            return RandomUtils.returnOneOfThese(random,Arrays.asList(K,M));
        } else if (spectralClass == M) {
            return M;
        }
        return null; // Other types unsupported.
    }
    
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
    
    
    
    
}

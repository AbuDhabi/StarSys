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
public enum ChunkClass {
    B (1.5*10e13/10e3,  4.0*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY),
    F (1.5*10e13/10e3,  4.5*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY),
    G (1.5*10e13/10e3,  4.0*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY),
    C (1.5*10e13/10e3,  4.0*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY),
    S (3.5*10e13/10e3,  5.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY),
    M (4.0*10e13/10e3,  8.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY),
    E (4.0*10e13/10e3,  8.0*10e13/10e3, 4e10, 4e20, 0.3, Color.WHITE),
    P (4.0*10e13/10e3,  8.0*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY), 
    A (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY), 
    D (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.05, Color.DARK_GRAY),
    T (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.2, Color.RED),
    Q (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY),
    R (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY),
    V (1.0*10e13/10e3, 10.0*10e13/10e3, 4e10, 4e20, 0.1, Color.GRAY);

    public static ChunkClass getRandomChunkClass(Random random) {
        int number = random.nextInt(100);
        if (number <= 70) {
            int bfgc = random.nextInt(4);
            if (bfgc == 0) return B;
            else if (bfgc == 1) return F;
            else if (bfgc == 2) return G;
            else if (bfgc == 3) return C;
        } else if (number > 70 && number <= 85) {
            return S;
        } else if (number > 85 && number <= 90) {
            int mep = random.nextInt(3);
            if (mep == 0) return M;
            else if (mep == 1) return E;
            else if (mep == 2) return P;
        } else {
            int adtqrv = random.nextInt(6);
            if (adtqrv == 0) return A;
            else if (adtqrv == 1) return D;
            else if (adtqrv == 2) return T;
            else if (adtqrv == 3) return Q;
            else if (adtqrv == 4) return R;
            else if (adtqrv == 5) return V;
        }
        return null; // Malfunction in the RNG!
    }
    
    // In kilograms per cubic kilometer.
    private final double lowerDensity;
    // In kilograms per cubic kilometer.
    private final double upperDensity;
    private final double lowerMass;
    private final double upperMass;
    private final double baseAlbedo;
    private final Color color;

    private ChunkClass(double lowerDensity, double upperDensity, double lowerMass, double upperMass, double baseAlbedo, Color color) {
        this.lowerDensity = lowerDensity;
        this.upperDensity = upperDensity;
        this.lowerMass = lowerMass;
        this.upperMass = upperMass;
        this.baseAlbedo = baseAlbedo;
        this.color = color;
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
     * @return the baseAlbedo
     */
    public double getBaseAlbedo() {
        return baseAlbedo;
    }
    
    
}

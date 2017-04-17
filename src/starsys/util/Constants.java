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

/**
 *
 * @author abudhabi
 */
public class Constants {
    // Kilograms.
    public static final double SUN_MASS = 1.98855e30;
    // Kilometers.
    public static final double SUN_RADIUS = 696342;
    // Kilograms.
    public static final double EARTH_MASS = 5.97237e24;
    // Kilometers.
    public static final double EARTH_RADIUS = 6371.0;
    
    // Kilometers.
    public static final double ASTRONOMICAL_UNIT = 1.4960e8;
    // Kilometers.
    public static final double KEPLER_70B_SEMIMAJOR_AXIS = 0.006*ASTRONOMICAL_UNIT;
    // Kilometers.
    public static final double MERCURY_SEMIMAJOR_AXIS = 57909050;
    
    // Hopefully in (J)/(km^2*day*K^4).
    public static final double STEFAN_BOLTZMAN_CONSTANT = (5.670367e-8)*(1000000*86400);
    // Hopefully in (km^2*kg)/(day^2).
    public static final double GRAVITATIONAL_CONSTANT = (6.673e-11)*(86400*86400/1000000000);
    
}

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

import java.awt.geom.Point2D;

/**
 *
 * @author abudhabi
 */
public class MassiveBody extends OrbitalPoint {

    public MassiveBody(long id, String name, double cachedTime, int offset, Point2D.Double center, OrbitalPoint parent, double semiMajorAxis, double angularVelocity, double eccentricity, double inclination) {
        super(id, name, cachedTime, offset, center, parent, semiMajorAxis, angularVelocity, eccentricity, inclination);
    }
    
}

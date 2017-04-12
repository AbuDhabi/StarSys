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
package starsys.model;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import starsys.util.Constants;

/**
 *
 * @author abudhabi
 */
public class Star extends MassiveBody {
    
    private final SpectralClass spectralClass;

    public Star(SpectralClass spectralClass, double tilt, double rotationVelocity, double mass, double radius, double temperature, double albedo, Color color, long id, String name, double cachedTime, int offset, Point2D.Double center, OrbitalPoint parent, List<OrbitalPoint> children, double semiMajorAxis, double angularVelocity, double eccentricity, double inclination) {
        super(tilt, rotationVelocity, mass, radius, temperature, albedo, color, id, name, cachedTime, offset, center, parent, children, semiMajorAxis, angularVelocity, eccentricity, inclination);
        this.spectralClass = spectralClass;
    }
    
    // In joules per day.
    public double getLuminosity() {
        return 4*Math.PI*Math.pow(radius, 2) * Constants.STEFAN_BOLTZMAN_CONSTANT * Math.pow(temperature, 4);
    }

    /**
     * @return the spectralClass
     */
    public SpectralClass getSpectralClass() {
        return spectralClass;
    }
    
}

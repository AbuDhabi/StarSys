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

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 *
 * @author abudhabi
 */
public abstract class MassiveBody extends OrbitalPoint {
    // Unused. Angle between orbital plane and rotation plane.
    protected final double tilt;
    // Unused. Radians per day. "How many local days happen during one standard day?"
    protected final double rotationVelocity;
    // Kilograms.
    protected final double mass;
    // Kilometers.
    protected final double radius;
    // Kelvin.
    protected double temperature;
    // 0-1. 0 - black, fully absorbent. 1 - white, fully reflectant.
    protected double albedo;
    protected Color color;

    public MassiveBody(double tilt, double rotationVelocity, double mass, double radius, double temperature, double albedo, Color color, long id, String name, double cachedTime, int offset, Point2D.Double center, OrbitalPoint parent, List<OrbitalPoint> children, double semiMajorAxis, double angularVelocity, double eccentricity, double inclination) {
        super(id, name, cachedTime, offset, center, parent, children, semiMajorAxis, angularVelocity, eccentricity, inclination);
        this.tilt = tilt;
        this.rotationVelocity = rotationVelocity;
        this.mass = mass;
        this.radius = radius;
        this.temperature = temperature;
        this.albedo = albedo;
        if (color == null) {
            this.color = Color.WHITE;
        } else {
            this.color = color;
        }
    }

    /**
     * @return the tilt
     */
    public double getTilt() {
        return tilt;
    }

    /**
     * @return the rotationVelocity
     */
    public double getRotationVelocity() {
        return rotationVelocity;
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return the temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the albedo
     */
    public double getAlbedo() {
        return albedo;
    }

    /**
     * @param albedo the albedo to set
     */
    public void setAlbedo(double albedo) {
        this.albedo = albedo;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }



    
}

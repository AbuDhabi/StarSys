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

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import starsys.model.Atmosphere;
import starsys.model.OrbitalPoint;
import starsys.util.SpectralClass;

/**
 * A collection of parameters that can be either set, or null. If they're null,
 * the generator will simply generate them for you according to its logic. If 
 * they are set, it will respect those instead. Be wary of setting dependent
 * variables! If you set stuff like mass and angular velocity, you may need to
 * also set the semi-major axis to match. 
 * @author abudhabi
 */
public class CelestialBodyParameters  {
    private String name; 
    private Double cachedTime;
    private Integer offset;
    private Point2D.Double center;
    private OrbitalPoint parent;
    private List<OrbitalPoint> children;
    private Double semiMajorAxis;
    private Double angularVelocity;
    private Double eccentricity;
    private Double inclination;
    private Double tilt;
    private Double rotationVelocity;
    private Double mass;
    private Double radius;
    private Double temperature;
    private Double albedo;
    private Color color;
    private Atmosphere atmosphere;
    private SpectralClass spectralClass;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @return 
     */
    public CelestialBodyParameters setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the cachedTime
     */
    public Double getCachedTime() {
        return cachedTime;
    }

    /**
     * @param cachedTime the cachedTime to set
     * @return 
     */
    public CelestialBodyParameters setCachedTime(Double cachedTime) {
        this.cachedTime = cachedTime;
        return this;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     * @return 
     */
    public CelestialBodyParameters setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /**
     * @return the center
     */
    public Point2D.Double getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     * @return 
     */
    public CelestialBodyParameters setCenter(Point2D.Double center) {
        this.center = center;
        return this;
    }

    /**
     * @return the parent
     */
    public OrbitalPoint getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     * @return 
     */
    public CelestialBodyParameters setParent(OrbitalPoint parent) {
        this.parent = parent;
        return this;
    }

    /**
     * @return the children
     */
    public List<OrbitalPoint> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     * @return 
     */
    public CelestialBodyParameters setChildren(List<OrbitalPoint> children) {
        this.children = children;
        return this;
    }

    /**
     * @return the semiMajorAxis
     */
    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * @param semiMajorAxis the semiMajorAxis to set
     * @return 
     */
    public CelestialBodyParameters setSemiMajorAxis(Double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
        return this;
    }

    /**
     * @return the angularVelocity
     */
    public Double getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * @param angularVelocity the angularVelocity to set
     * @return 
     */
    public CelestialBodyParameters setAngularVelocity(Double angularVelocity) {
        this.angularVelocity = angularVelocity;
        return this;
    }

    /**
     * @return the eccentricity
     */
    public Double getEccentricity() {
        return eccentricity;
    }

    /**
     * @param eccentricity the eccentricity to set
     * @return 
     */
    public CelestialBodyParameters setEccentricity(Double eccentricity) {
        this.eccentricity = eccentricity;
        return this;
    }

    /**
     * @return the inclination
     */
    public Double getInclination() {
        return inclination;
    }

    /**
     * @param inclination the inclination to set
     * @return 
     */
    public CelestialBodyParameters setInclination(Double inclination) {
        this.inclination = inclination;
        return this;
    }

    /**
     * @return the tilt
     */
    public Double getTilt() {
        return tilt;
    }

    /**
     * @param tilt the tilt to set
     * @return 
     */
    public CelestialBodyParameters setTilt(Double tilt) {
        this.tilt = tilt;
        return this;
    }

    /**
     * @return the rotationVelocity
     */
    public Double getRotationVelocity() {
        return rotationVelocity;
    }

    /**
     * @param rotationVelocity the rotationVelocity to set
     * @return 
     */
    public CelestialBodyParameters setRotationVelocity(Double rotationVelocity) {
        this.rotationVelocity = rotationVelocity;
        return this;
    }

    /**
     * @return the mass
     */
    public Double getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     * @return 
     */
    public CelestialBodyParameters setMass(Double mass) {
        this.mass = mass;
        return this;
    }

    /**
     * @return the radius
     */
    public Double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     * @return 
     */
    public CelestialBodyParameters setRadius(Double radius) {
        this.radius = radius;
        return this;
    }

    /**
     * @return the temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     * @return 
     */
    public CelestialBodyParameters setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * @return the albedo
     */
    public Double getAlbedo() {
        return albedo;
    }

    /**
     * @param albedo the albedo to set
     * @return 
     */
    public CelestialBodyParameters setAlbedo(Double albedo) {
        this.albedo = albedo;
        return this;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     * @return 
     */
    public CelestialBodyParameters setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * @return the atmosphere
     */
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    /**
     * @param atmosphere
     * @return the atmosphere to set
     */
    public CelestialBodyParameters setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
        return this;
    }

    /**
     * @return the spectralClass
     */
    public SpectralClass getSpectralClass() {
        return spectralClass;
    }

    /**
     * @param spectralClass the spectralClass to set
     * @return 
     */
    public CelestialBodyParameters setSpectralClass(SpectralClass spectralClass) {
        this.spectralClass = spectralClass;
        return this;
    }
    
}

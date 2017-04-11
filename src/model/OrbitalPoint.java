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
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author abudhabi
 */
public class OrbitalPoint {
    // Unique object identifier of this class and its descendants. 
    protected final long id;
    protected String name; 
    // One day per unit of time.
    public static final int MILLIS_PER_TIME_UNIT = 1000*60*60*24; 
    protected double cachedTime;
    // Offset is added to time, resulting in more 'uniqueness'.
    protected final int offset;
    // If there is no parent (primary), then center is the reference point.
    protected final Point2D.Double center;
    // If not null, then this is what this object orbits, and used as its center.
    protected final OrbitalPoint parent;
    // All orbiters.
    protected final List<OrbitalPoint> children;
    // Kilometers.
    protected final double semiMajorAxis;
    // Radians per day.
    protected final double angularVelocity;
    // Unused. 0 - circular, 1 - parabolic. 
    protected final double eccentricity;
    // Unused. Angle between orbital plane and reference plane.
    protected final double inclination;


    public OrbitalPoint(long id, String name, double cachedTime, int offset, Point2D.Double center, OrbitalPoint parent, List<OrbitalPoint> children, double semiMajorAxis, double angularVelocity, double eccentricity, double inclination) {
        this.id = id;
        this.cachedTime = cachedTime;
        this.offset = offset;
        this.parent = parent;
        this.semiMajorAxis = semiMajorAxis;
        this.angularVelocity = angularVelocity;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        
        if (name == null) {
            this.name = "Orbital Node #"+id;
        } else {
            this.name = name;
        }
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            this.children = children;
        }
        if (center == null) {
            this.center = new Point2D.Double(); // Implicitly (0,0).
        } else {
            this.center = center;
        }
    }
    
    public double getX() { return getX(cachedTime);  }
    
    public double getX(double time) {
        if (getParent() != null) {
            return parent.getX(time) + semiMajorAxis * Math.cos(offset + time * angularVelocity);
        } else {
            return center.getX() + semiMajorAxis * Math.cos(offset + time * angularVelocity);
        }
    }
    
    public double getY() { return getY(cachedTime);  }
    
    public double getY(double time) {
        if (getParent() != null) {
            return parent.getY(time) + semiMajorAxis * Math.sin(offset + time * angularVelocity);
        } else {
            return center.getY() + semiMajorAxis * Math.sin(offset + time * angularVelocity);
        }
    }
    
    public double getZ() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the cachedTime
     */
    public double getCachedTime() {
        return cachedTime;
    }

    /**
     * @param cachedTime the cachedTime to set
     */
    public void setCachedTime(double cachedTime) {
        this.cachedTime = cachedTime;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @return the center
     */
    public Point2D.Double getCenter() {
        return center;
    }

    /**
     * @return the parent
     */
    public OrbitalPoint getParent() {
        return parent;
    }

    /**
     * @return the semiMajorAxis
     */
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * @return the angularVelocity
     */
    public double getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * @return the eccentricity
     */
    public double getEccentricity() {
        return eccentricity;
    }

    /**
     * @return the inclination
     */
    public double getInclination() {
        return inclination;
    }

    /**
     * @return the children
     */
    public List<OrbitalPoint> getChildren() {
        return children;
    }
}

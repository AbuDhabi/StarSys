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

import java.awt.Point;

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
    protected long cachedTime;
    // Offset is added to time, resulting in more 'uniqueness'.
    protected final int offset;
    // If there is no parent (primary), then center is the reference point.
    protected final Point center;
    // If not null, then this is what this object orbits, and used as its center.
    protected final OrbitalPoint parent;
    // Kilometers.
    protected final double semiMajorAxis;
    // Radians per day.
    protected final double angularVelocity;
    // Unused. 0 - circular, 1 - parabolic. 
    protected final double eccentricity;
    // Unused. Angle between orbital plane and reference plane.
    protected final double inclination;
    // Unused. Angle between orbital plane and rotation plane.
    protected double tilt;

    public OrbitalPoint(long id, String name, long cachedTime, int offset, Point center, OrbitalPoint parent, double semiMajorAxis, double angularVelocity, double eccentricity, double inclination, double tilt) {
        this.id = id;
        this.name = name;
        this.cachedTime = cachedTime;
        this.offset = offset;
        this.center = center;
        this.parent = parent;
        this.semiMajorAxis = semiMajorAxis;
        this.angularVelocity = angularVelocity;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.tilt = tilt;
    }
    
    public double getX() { return getX(cachedTime);  }
    
    public double getX(long time) {
        if (getParent() != null) {
            return getParent().getX(time) + semiMajorAxis * Math.cos(getOffset() + time * angularVelocity);
        } else {
            return center.getX() + semiMajorAxis * Math.cos(getOffset() + time * angularVelocity);
        }
    }
    
    public double getY() { return getY(cachedTime);  }
    
    public double getY(long time) {
        if (getParent() != null) {
            return getParent().getY(time) + semiMajorAxis * Math.sin(offset + time * angularVelocity);
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
    public long getCachedTime() {
        return cachedTime;
    }

    /**
     * @param cachedTime the cachedTime to set
     */
    public void setCachedTime(long cachedTime) {
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
    public Point getCenter() {
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
     * @return the tilt
     */
    public double getTilt() {
        return tilt;
    }

    /**
     * @param tilt the tilt to set
     */
    public void setTilt(double tilt) {
        this.tilt = tilt;
    }
}

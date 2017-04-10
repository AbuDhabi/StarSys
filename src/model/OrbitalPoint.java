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

/**
 * 
 * @author abudhabi
 */
public class OrbitalPoint {
    private long id;
    private long cachedTime;
    
    public double getX() {
        throw new UnsupportedOperationException("");
    }
    
    public double getY() {
        throw new UnsupportedOperationException("");
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
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}

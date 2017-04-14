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

import java.util.List;
import starsys.util.SpectralClass;

/**
 *
 * @author abudhabi
 */
public class SystemParameters {
    private Integer nrofDirectChildren;
    private Boolean grandChildrenAllowed;
    private Integer nrofTerrestrials;
    private Integer nrofChunksPerBelt;
    private Integer nrofChunkBelts;
    private Integer nrofGasGiants;
    private Integer nrofSuns;
    private SpectralClass primarySpectralClass;
    private List<SpectralClass> companionSpectralClasses;

    /**
     * @return the nrofDirectChildren
     */
    public Integer getNrofDirectChildren() {
        return nrofDirectChildren;
    }

    /**
     * @param nrofChildren the nrofDirectChildren to set
     * @return 
     */
    public SystemParameters setNrofChildren(Integer nrofChildren) {
        this.nrofDirectChildren = nrofChildren;
        return this;
    }

    /**
     * @return the nrofTerrestrials
     */
    public Integer getNrofTerrestrials() {
        return nrofTerrestrials;
    }

    /**
     * @param nrofTerrestrials the nrofTerrestrials to set
     * @return 
     */
    public SystemParameters setNrofTerrestrials(Integer nrofTerrestrials) {
        this.nrofTerrestrials = nrofTerrestrials;
        return this;
    }

    /**
     * @return the nrofChunksPerBelt
     */
    public Integer getNrofChunksPerBelt() {
        return nrofChunksPerBelt;
    }

    /**
     * @param nrofChunksPerBelt the nrofChunksPerBelt to set
     * @return 
     */
    public SystemParameters setNrofChunksPerBelt(Integer nrofChunksPerBelt) {
        this.nrofChunksPerBelt = nrofChunksPerBelt;
        return this;
    }

    /**
     * @return the nrofChunkBelts
     */
    public Integer getNrofChunkBelts() {
        return nrofChunkBelts;
    }

    /**
     * @param nrofChunkBelts the nrofChunkBelts to set
     * @return 
     */
    public SystemParameters setNrofChunkBelts(Integer nrofChunkBelts) {
        this.nrofChunkBelts = nrofChunkBelts;
        return this;
    }

    /**
     * @return the nrofGasGiants
     */
    public Integer getNrofGasGiants() {
        return nrofGasGiants;
    }

    /**
     * @param nrofGasGiants the nrofGasGiants to set
     * @return 
     */
    public SystemParameters setNrofGasGiants(Integer nrofGasGiants) {
        this.nrofGasGiants = nrofGasGiants;
        return this;
    }

    /**
     * @return the nrofSuns
     */
    public Integer getNrofSuns() {
        return nrofSuns;
    }

    /**
     * @param nrofSuns the nrofSuns to set
     * @return 
     */
    public SystemParameters setNrofSuns(Integer nrofSuns) {
        this.nrofSuns = nrofSuns;
        return this;
    }

    /**
     * @return the primarySpectralClass
     */
    public SpectralClass getPrimarySpectralClass() {
        return primarySpectralClass;
    }

    /**
     * @param primarySpectralClass the primarySpectralClass to set
     * @return 
     */
    public SystemParameters setPrimarySpectralClass(SpectralClass primarySpectralClass) {
        this.primarySpectralClass = primarySpectralClass;
        return this;
    }

    /**
     * @return the companionSpectralClasses
     */
    public List<SpectralClass> getCompanionSpectralClasses() {
        return companionSpectralClasses;
    }

    /**
     * @param companionSpectralClasses the companionSpectralClasses to set
     * @return 
     */
    public SystemParameters setCompanionSpectralClasses(List<SpectralClass> companionSpectralClasses) {
        this.companionSpectralClasses = companionSpectralClasses;
        return this;
    }

    /**
     * @return the grandChildrenAllowed
     */
    public Boolean isGrandChildrenAllowed() {
        return grandChildrenAllowed;
    }

    /**
     * @param grandChildrenAllowed the grandChildrenAllowed to set
     * @return 
     */
    public SystemParameters setGrandChildrenAllowed(Boolean grandChildrenAllowed) {
        this.grandChildrenAllowed = grandChildrenAllowed;
        return this;
    }
    
    
}

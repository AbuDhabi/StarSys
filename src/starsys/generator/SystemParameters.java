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

import java.util.ArrayList;
import java.util.List;
import starsys.model.OrbitalPoint;


/**
 *
 * @author abudhabi
 */
public class SystemParameters {
    private SystemParameters parent;
    private CelestialBodyParameters specification;
    private boolean childrenAllowed;
    private List<SystemParameters> children;

    public SystemParameters(SystemParameters parent, CelestialBodyParameters specification, boolean childrenAllowed, List<SystemParameters> children) {
        this.parent = parent;
        if (specification == null) {
            this.specification = new CelestialBodyParameters();
        } else {
           this.specification = specification;
        }
        this.childrenAllowed = childrenAllowed;
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            this.children = children;
        }
    }

    /**
     * @return the parent
     */
    public SystemParameters getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(SystemParameters parent) {
        this.parent = parent;
    }

    /**
     * @return the specification
     */
    public CelestialBodyParameters getSpecification() {
        return specification;
    }

    /**
     * @param specification the specification to set
     */
    public void setSpecification(CelestialBodyParameters specification) {
        this.specification = specification;
    }

    /**
     * @return the childrenAllowed
     */
    public boolean isChildrenAllowed() {
        return childrenAllowed;
    }

    /**
     * @param childrenAllowed the childrenAllowed to set
     */
    public void setChildrenAllowed(boolean childrenAllowed) {
        this.childrenAllowed = childrenAllowed;
    }

    /**
     * @return the children
     */
    public List<SystemParameters> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<SystemParameters> children) {
        this.children = children;
    }
    
}

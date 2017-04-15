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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author abudhabi
 */
public class Atmosphere {
    // In bars/atmospheres. Here mostly for convenience/peformance.
    private double pressure;
    private final Map<Gas,Double> composition;

    public Atmosphere() {
        this.pressure = 0.0;
        this.composition = new HashMap<>();
    }

    public Atmosphere(Map<Gas,Double> composition) {
        if (composition == null) {
            this.pressure = 0.0;
            this.composition = new HashMap<>();
        } else {
            this.pressure = calculatePressure(composition);
            this.composition = composition;
        }
    }
    
    private double calculatePressure() {
        return calculatePressure(this.composition);
    }
    
    private double calculatePressure(Map<Gas,Double> compos) {
        double pres = 0;
        for (Entry<Gas,Double> entry: compos.entrySet()) {
            pres = pres + entry.getValue();
        }
        pressure = pres;
        return pres;
    }
    
    public void addGas(Gas gas, double pressure) {
        if (composition.get(gas) != null) {
            double oldPressure = composition.get(gas);
            composition.put(gas, oldPressure + pressure);
        } else {
            composition.put(gas,pressure);
        }
        calculatePressure();
    }
    
    public void removeGas(Gas gas, double pressure) {
        if (composition.get(gas) != null) {
            if (composition.get(gas) > pressure) {
                double oldPressure = composition.get(gas);
                composition.put(gas, oldPressure-pressure);
            } else {
                composition.remove(gas);
            }
        }
        calculatePressure();
    }
    
    public void removeAllGases() {
        composition.clear();
        calculatePressure();
    }

    /**
     * @return the pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @return the composition
     */
    public Map<Gas,Double> getComposition() {
        return new HashMap<>(composition);
    }
    
}

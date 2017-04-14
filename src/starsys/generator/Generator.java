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

import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.model.MassiveBody;
import starsys.model.Star;
import starsys.model.Terrestrial;

/**
 *
 * @author abudhabi
 */
public interface Generator {
    
    public Star generateStar(CelestialBodyParameters params);
    
    public Terrestrial generateTerrestrialWorld(CelestialBodyParameters params);
    
    public Chunk generateChunk(CelestialBodyParameters params);
    
    public GasGiant generateGasGiant(CelestialBodyParameters params);
    
    public Star generateStarSystem(SystemParameters params);
    
    public MassiveBody populatePlanetWithMoons(SystemParameters params);
}

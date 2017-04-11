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
package generator;

import model.Chunk;
import model.GasGiant;
import model.MassiveBody;
import model.OrbitalPoint;
import model.Star;
import model.Terrestrial;

/**
 *
 * @author abudhabi
 */
public interface Generator {
    
    public Star generateRandomStar(String spectralClass, OrbitalPoint parent);
    
    public Terrestrial generateRandomTerrestrialWorld(OrbitalPoint parent);
    
    public Chunk generateRandomChunk(OrbitalPoint parent);
    
    public GasGiant generateRandomGasGiant(OrbitalPoint parent);
    
    public Star generateRandomStarSystem(int nrofSuns, String spectralClassOfPrimary);
    
    public MassiveBody populatePlanetWithMoons(int nrofMoons, MassiveBody parent);
}

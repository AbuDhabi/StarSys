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

import starsys.util.SpectralClass;
import starsys.model.Star;
import starsys.model.Terrestrial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.util.ChunkClass;
import starsys.util.GasGiantClass;
import starsys.util.TerrestrialClass;

/**
 *
 * @author abudhabi
 */
public class DefaultGeneratorTest {
    
    public DefaultGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGenerateRandomAClassStar() {
        Generator g = new DefaultGenerator(0L);
        Star star = g.generateStar(new CelestialBodyParameters().setSpectralClass(SpectralClass.A));
        System.out.println(star.toJson());
        
        assertTrue(star.getSpectralClass() == SpectralClass.A);
        assertTrue(star.getMass() >= SpectralClass.A.getLowerMass());
        assertTrue(star.getMass() <= SpectralClass.A.getUpperMass());
        assertTrue(star.getRadius() >= SpectralClass.A.getLowerRadius());
        assertTrue(star.getRadius() <= SpectralClass.A.getUpperRadius());
        assertTrue(star.getTemperature()>= SpectralClass.A.getLowerTemperature());
        assertTrue(star.getTemperature() <= SpectralClass.A.getUpperTemperature());
        assertTrue(star.getParent() == null);
        assertTrue(star.getSemiMajorAxis() == 0);
    }
    
    @Test
    public void testGenerateTerrestrialWorld() {
        Generator g = new DefaultGenerator(0L);
        Terrestrial t = g.generateTerrestrialWorld(new CelestialBodyParameters().setTerrestrialClass(TerrestrialClass.SILICATE));
        System.out.println(t.toJson());
        
        assertTrue(t.getTerrestrialClass() == TerrestrialClass.SILICATE);
        assertTrue(t.getMass() >= TerrestrialClass.SILICATE.getLowerMass());
        assertTrue(t.getMass() <= TerrestrialClass.SILICATE.getUpperMass());
        assertTrue(t.getDensity() >= TerrestrialClass.SILICATE.getLowerDensity());
        assertTrue(t.getDensity() <= TerrestrialClass.SILICATE.getUpperDensity());
        assertTrue(t.getParent() == null);
        assertTrue(t.getSemiMajorAxis() == 0);
    }

    @Test
    public void testGenerateChunk() {
        Generator g = new DefaultGenerator(0L);
        Chunk c = g.generateChunk(new CelestialBodyParameters().setChunkClass(ChunkClass.C));
        System.out.println(c.toJson());
        
        assertTrue(c.getChunkClass() == ChunkClass.C);
        assertTrue(c.getMass() >= ChunkClass.C.getLowerMass());
        assertTrue(c.getMass() <= ChunkClass.C.getUpperMass());
        assertTrue(c.getDensity() >= ChunkClass.C.getLowerDensity());
        assertTrue(c.getDensity() <= ChunkClass.C.getUpperDensity());
        assertTrue(c.getParent() == null);
        assertTrue(c.getSemiMajorAxis() == 0);
    }
    
    @Test
    public void testGenerateGasGiant() {
        Generator g = new DefaultGenerator(0L);
        GasGiant gc = g.generateGasGiant(new CelestialBodyParameters().setGasGiantClass(GasGiantClass.III));
        System.out.println(gc.toJson());
        
        assertTrue(gc.getGasGiantClass() == GasGiantClass.III);
        assertTrue(gc.getMass() >= GasGiantClass.III.getLowerMass());
        assertTrue(gc.getMass() <= GasGiantClass.III.getUpperMass());
        assertTrue(gc.getDensity() >= GasGiantClass.III.getLowerDensity());
        assertTrue(gc.getDensity() <= GasGiantClass.III.getUpperDensity());
        assertTrue(gc.getParent() == null);
        assertTrue(gc.getSemiMajorAxis() == 0);
    }
    
}

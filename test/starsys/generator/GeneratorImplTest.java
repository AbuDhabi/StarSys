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

import starsys.generator.Generator;
import starsys.generator.GeneratorImpl;
import java.util.Random;
import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.model.MassiveBody;
import starsys.model.OrbitalPoint;
import starsys.model.SpectralClass;
import starsys.model.Star;
import starsys.model.Terrestrial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abudhabi
 */
public class GeneratorImplTest {
    
    public GeneratorImplTest() {
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

    /**
     * Test of generateRandomStar method, of class GeneratorImpl.
     */
    @Test
    public void testGenerateRandomStar() {
        Generator g = new GeneratorImpl(new Random());
        System.out.println(g.generateRandomStar(SpectralClass.A, null).toJson());
    }

    /**
     * Test of generateRandomTerrestrialWorld method, of class GeneratorImpl.
     */
    @Test
    public void testGenerateRandomTerrestrialWorld() {
        System.out.println("generateRandomTerrestrialWorld");
        OrbitalPoint parent = null;
        GeneratorImpl instance = null;
        Terrestrial expResult = null;
        Terrestrial result = instance.generateRandomTerrestrialWorld(parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomChunk method, of class GeneratorImpl.
     */
    @Test
    public void testGenerateRandomChunk() {
        System.out.println("generateRandomChunk");
        OrbitalPoint parent = null;
        GeneratorImpl instance = null;
        Chunk expResult = null;
        Chunk result = instance.generateRandomChunk(parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomGasGiant method, of class GeneratorImpl.
     */
    @Test
    public void testGenerateRandomGasGiant() {
        System.out.println("generateRandomGasGiant");
        OrbitalPoint parent = null;
        GeneratorImpl instance = null;
        GasGiant expResult = null;
        GasGiant result = instance.generateRandomGasGiant(parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomStarSystem method, of class GeneratorImpl.
     */
    @Test
    public void testGenerateRandomStarSystem() {
        System.out.println("generateRandomStarSystem");
        int nrofSuns = 0;
        String spectralClassOfPrimary = "";
        GeneratorImpl instance = null;
        Star expResult = null;
        Star result = instance.generateRandomStarSystem(nrofSuns, spectralClassOfPrimary);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populatePlanetWithMoons method, of class GeneratorImpl.
     */
    @Test
    public void testPopulatePlanetWithMoons() {
        System.out.println("populatePlanetWithMoons");
        int nrofMoons = 0;
        MassiveBody parent = null;
        GeneratorImpl instance = null;
        MassiveBody expResult = null;
        MassiveBody result = instance.populatePlanetWithMoons(nrofMoons, parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

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
import starsys.util.SpectralClass;
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
    public void testGenerateRandomAClassStar() {
        Generator g = new GeneratorImpl(new Random());
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

    
}

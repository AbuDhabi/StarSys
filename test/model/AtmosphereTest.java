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
public class AtmosphereTest {
    
    public AtmosphereTest() {
    }
    
    Gas co2;
    Gas o2;
    Atmosphere atm;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        atm = new Atmosphere();
        co2 = new Gas("Carbon Dioxide","CO2");
        o2 = new Gas("Dioxygen","O2");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInitialization() {
        assertTrue(atm.getPressure() == 0);
    }

    /**
     * Test of addGas method, of class Atmosphere.
     */
    @Test
    public void testAddGas() {
        atm.addGas(co2, 1.0);
        atm.addGas(o2, 0.5);
        assertTrue(atm.getPressure() == 1.5);
        assertTrue(atm.getComposition().size() == 2);
        assertTrue(atm.getComposition().containsKey(o2));
        assertTrue(atm.getComposition().containsKey(co2));
    }

    /**
     * Test of removeGas method, of class Atmosphere.
     */
    @Test
    public void testRemoveGas() {
        atm.addGas(co2, 1.0);
        atm.addGas(o2, 0.5);
        atm.removeGas(co2, 0.5);
        assertTrue(atm.getPressure() == 1.0);
        assertTrue(atm.getComposition().size() == 2);
        assertTrue(atm.getComposition().containsKey(o2));
        assertTrue(atm.getComposition().containsKey(co2));
        atm.removeGas(o2, 1.0);
        assertTrue(atm.getPressure() == 0.5);
        assertTrue(atm.getComposition().size() == 1);
        assertTrue(!atm.getComposition().containsKey(o2));
        assertTrue(atm.getComposition().containsKey(co2));
    }

    
}

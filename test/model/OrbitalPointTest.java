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

import java.awt.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abudhabi
 */
public class OrbitalPointTest {
    
    public OrbitalPointTest() {
    }
    
    OrbitalPoint erf; 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("Instantiating object with Earth's properties.");
        erf = new OrbitalPoint(
            0L,
            "Earth",
            123L,
            456, 
            new Point(0,0), 
            null,
            149598023, // semi major axis
            2*Math.PI/365, // angular velocity 
            0,
            0,
            0
        );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class OrbitalPoint.
     */
    @Test
    public void testMasslessEarthMakingRoundTrips() {
        // Round trip around the non-existent Sun takes 365 days. So the X and Y should be the same every 365 days.
        // Or close enough. Stupid large numbers.
        // If the difference is under 0.1 of a kilometer, that's close enough for govt work.
        for (int i=0;i<1000;i++) {
            assertTrue(Math.abs(erf.getX(0L)-erf.getX(365L*i)) < 0.1);
            assertTrue(Math.abs(erf.getY(0L)-erf.getY(365L*i)) < 0.1);
        }
    }

}

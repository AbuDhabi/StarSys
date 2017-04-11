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

import java.awt.geom.Point2D;
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
public class OrbitalPointTest {
    
    public OrbitalPointTest() {
    }
    
    double year = 365.25;
    double siderealMonth = 27.32;
    OrbitalPoint erf; 
    OrbitalPoint mun;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        erf = new OrbitalPoint(
            0L,
            "Earth",
            123L,
            456, 
            new Point2D.Double(0,0), 
            null, // parent
            null, // children
            149598023, // semi major axis
            2*Math.PI/year, // angular velocity 
            0,
            0
        );
        mun = new OrbitalPoint(
            0L,
            "Moon",
            456L,
            789, 
            null, // center, unused if child
            erf,
            null, // children
            384399, // semi major axis
            2*Math.PI/siderealMonth, // angular velocity 
            0,
            0
        );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Round trip around the non-existent Sun takes 365 days. So the X and Y should be the same every 365 days.
     * Or close enough. Stupid large numbers.
     * If the difference is under 0.1 of a kilometer, that's close enough for government work.
     */
    @Test
    public void testMasslessEarthMakingRoundTrips() {
        for (int i=0;i<1000;i++) {
            assertTrue(Math.abs(erf.getX(0L)-erf.getX(year*i)) < 0.1);
            assertTrue(Math.abs(erf.getY(0L)-erf.getY(year*i)) < 0.1);
        }
    }
    
    /**
     * If you subtract the parent's movement from the child's movement, it should give the same location
     * at the same point in the cycle.
     */
    @Test
    public void testMasslessMoonMakingRoundTripsAroundEarth() {
        double startTime = 0;
        double endTime = siderealMonth;
        Point2D.Double erfPositionAtStartTime = new Point2D.Double(erf.getX(startTime),erf.getY(startTime));
        Point2D.Double erfPositionAtEndTime = new Point2D.Double(erf.getX(endTime),erf.getY(endTime));
        Point2D.Double munPositionAtStartTime = new Point2D.Double(mun.getX(startTime),mun.getY(startTime));
        Point2D.Double munPositionAtEndTime = new Point2D.Double(mun.getX(endTime),mun.getY(endTime));

        Point2D.Double munMinusErfPositionStartTime = new Point2D.Double(
                munPositionAtStartTime.getX()-erfPositionAtStartTime.getX(),
                munPositionAtStartTime.getY()-erfPositionAtStartTime.getY()
        );
        Point2D.Double munMinusErfPositionEndTime = new Point2D.Double(
                munPositionAtEndTime.getX()-erfPositionAtEndTime.getX(),
                munPositionAtEndTime.getY()-erfPositionAtEndTime.getY()
        );
        assertTrue(Math.abs(munMinusErfPositionStartTime.getX() - munMinusErfPositionEndTime.getX()) < 0.1);
        assertTrue(Math.abs(munMinusErfPositionStartTime.getY() - munMinusErfPositionEndTime.getY()) < 0.1);
    }

}

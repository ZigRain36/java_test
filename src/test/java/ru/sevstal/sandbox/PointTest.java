package ru.sevstal.sandbox;


import org.testng.Assert;
import org.testng.annotations.*;

public class PointTest {
    @Test
    public void testPoint() {
        Point p1 = new Point(8, 4);
        Point p2 = new Point(16, 4);
        Assert.assertEquals(p1.distance(p2), 8.0);
   }

}

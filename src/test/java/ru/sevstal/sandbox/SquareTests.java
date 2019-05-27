package ru.sevstal.sandbox;

import org.junit.Assert;
import org.junit.Test;
import org.testng.*;

public class SquareTests {
@Test
    public void testArea() {

        Square s = new Square(5);
        assert s.area() == 25;

    }
}

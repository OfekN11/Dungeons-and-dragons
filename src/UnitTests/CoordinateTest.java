package UnitTests;

import Backend.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    static Coordinate c1;
    static Coordinate c2;
    static Coordinate c3;
    @BeforeAll
    static void setUp(){
        c1 = new Coordinate(1,2);
        c2 = new Coordinate(1, -2);
        c3 = new Coordinate(3,1);
    }
    @Test
    void range() {
        assertEquals(4,c1.range(c2));
        assertEquals(Math.sqrt(5),c1.range(c3));
    }

    @Test
    void compareTo() {
        assertEquals(1,c1.compareTo(c2));
    }
}
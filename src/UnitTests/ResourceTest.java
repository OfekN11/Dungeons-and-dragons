package UnitTests;

import Backend.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
    static Resource r1;
    static Resource r2;
    static Resource r3;
    @BeforeAll
    static void setUp() {
        r1 = new Resource(50);
        r2 = new Resource(30);
        r3 = new Resource(40,10);
    }

    @Test
    void reduce() {
        r1.reduce(40);
        assertEquals(10,r1.getAmount());
        r1.reduce(30);
        assertEquals(0,r1.getAmount());
        r2.reduce(20);
        assertEquals(10,r2.getAmount());

    }

    @Test
    void fillToMax() {
        r3.fillToMax();
        assertEquals(40,r3.getAmount());

    }

    @Test
    void fill() {

    }

    @Test
    void addCapacity() {
    }
}
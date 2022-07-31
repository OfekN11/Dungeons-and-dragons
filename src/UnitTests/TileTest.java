package UnitTests;

import Backend.Mage;
import Backend.Tile;
import Backend.Warrior;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    static Tile t1;
    static Tile t2;
    @BeforeAll
    static void setUp(){
        t1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        t2 = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
    }

    @Test
    void testToString() {
        assertEquals("@",t1.toString());
        assertEquals("@",t2.toString());
    }
}
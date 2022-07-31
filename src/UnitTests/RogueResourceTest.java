package UnitTests;

import Backend.RogueResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RogueResourceTest
{
    static RogueResource r1;
    static RogueResource r2;
    @BeforeAll
    static void setUp(){
        r1 = new RogueResource(100);
        r2 = new RogueResource(50);
    }

    @Test
    void tick() {
        r1.tick();
        r2.tick();
        assertEquals(100,r1.getCurrentEnergy());
        assertEquals(100,r2.getCurrentEnergy());

    }

    @Test
    void levelUpUPDATE() {
        r1.levelUpUPDATE();
        r2.levelUpUPDATE();
        assertEquals(100,r1.getCurrentEnergy());
        assertEquals(100,r2.getCurrentEnergy());
    }

    @Test
    void castAbility() {
        r1.CastAbility();
        r2.CastAbility();
        assertEquals(0,r1.getCurrentEnergy());
        assertEquals(50,r2.getCurrentEnergy());

    }
}
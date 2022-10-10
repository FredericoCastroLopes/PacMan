package com.aor.hero.model.elements;

import com.aor.hero.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GhostTest {

    @Test
    void setPosition(){
        Ghost ghost = new Ghost(10, 15);
        ghost.setPosition(new Position(10,15));

        Assertions.assertEquals(10, ghost.getPosition().getX());
        Assertions.assertEquals(15,ghost.getPosition().getY());
    }

    @Test
    void isAlive_setAlive() {
        Ghost ghost = new Ghost(10,10);
        Assertions.assertEquals(true, ghost.isAlive());
        ghost.setAlive(false);
        Assertions.assertEquals(false, ghost.isAlive());
        ghost.setAlive(true);
        Assertions.assertEquals(true, ghost.isAlive());
    }

    @Test
    void isPowerON_setPowerON() {
        Ghost ghost = new Ghost(10,10);
        Assertions.assertEquals(false, ghost.isPowerON());
        ghost.setPowerON(true);
        Assertions.assertEquals(true, ghost.isPowerON());
    }

    @Test
    void getLastPosition() {
        Ghost ghost = new Ghost(10,15);
        ghost.setPosition(new Position(12,13));

        Assertions.assertEquals(10, ghost.getLast_position().getX());
        Assertions.assertEquals(15, ghost.getLast_position().getY());

        ghost.setPosition(new Position(11,17));

        Assertions.assertEquals(12, ghost.getLast_position().getX());
        Assertions.assertEquals(13, ghost.getLast_position().getY());
    }
}


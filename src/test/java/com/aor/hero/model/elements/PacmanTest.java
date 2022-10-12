package com.aor.hero.model.elements;

import com.aor.hero.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PacmanTest {

    @Test
    void setPosition() {
        Pacman pacman = new Pacman(10, 15);
        pacman.setPosition(new Position(10, 15));

        Assertions.assertEquals(10, pacman.getPosition().getX());
        Assertions.assertEquals(15, pacman.getPosition().getY());
    }

    @Test
    void testLife() {

        //given
        Pacman pacman = new Pacman(10,15); //tem 3 vidas por default

        Assertions.assertEquals(3,pacman.getLifes());

        //when
        pacman.decreaseLifes();

        //then
        Assertions.assertEquals(2, pacman.getLifes());
    }

    @Test
    void testSide() {

        Pacman pacman = new Pacman(10,15); //default side 'p'

        Assertions.assertEquals('p', pacman.getSide());

        pacman.setSide('w');

        Assertions.assertEquals('w', pacman.getSide());

    }

    @Test
    void testPowerStatus() {

        Pacman pacman = new Pacman(10,15); //default power_status = false

        Assertions.assertEquals(false, pacman.isPower_status());

        pacman.setPower_status(true);

        Assertions.assertEquals(true, pacman.isPower_status());

    }

    @Test
    void testScore() {

        Pacman pacman = new Pacman(10,15); //default score = 0

        Assertions.assertEquals(0, pacman.getScore());

        pacman.increaseScore(150);

        Assertions.assertEquals(150, pacman.getScore());

        pacman.scoretoString();

        Assertions.assertEquals("0150", pacman.scoretoString());
    }

    @Test
    void reduceExtraPower(){
        Pacman pacman = new Pacman(10,15);
        pacman.setPower_status(true);
        Assertions.assertEquals(0,pacman.getExtra_powers());
        Assertions.assertTrue(pacman.isPower_status());
        pacman.setPower_status(true);
        Assertions.assertEquals(1,pacman.getExtra_powers());
    }
}

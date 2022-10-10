package com.aor.hero.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PositionTest {

    @Test
    void testPosition() {
        Position position = new Position(10,15);

        Assertions.assertEquals(10, position.getX());
        Assertions.assertEquals(15,position.getY());
    }

    @Test
    void testMovement() {
        Position position = new Position(10, 15);
        Position position1 = position.getUp();
        Position position2 = position.getRight();
        Position position3 = position.getDown();
        Position position4 = position.getLeft();

        //getUP
        Assertions.assertEquals(position1.getX(),10);
        Assertions.assertEquals(position1.getY(),14);

        //getRight
        Assertions.assertEquals(position2.getX(),11);
        Assertions.assertEquals(position2.getY(),15);

        //getDown
        Assertions.assertEquals(position3.getX(),10);
        Assertions.assertEquals(position3.getY(),16);

        //getLeft
        Assertions.assertEquals(position4.getX(),9);
        Assertions.assertEquals(position4.getY(),15);
    }

    @Test
    void testRandomMovement(){
        Position position = Mockito.spy(new Position(1,1));
        for (int i =0 ; i<200 ; i++) {
            position.getRandomNeighbour();
        }

        Mockito.verify(position,Mockito.atLeast(1)).getUp();
        Mockito.verify(position,Mockito.atLeast(1)).getRight() ;
        Mockito.verify(position,Mockito.atLeast(1)).getDown();
        Mockito.verify(position,Mockito.atLeast(1)).getLeft();
    }

    @Test
    void testequals(){
        Position position1 = new Position(1,1);
        Position position2 = new Position(1,1);
        Position position3 = new Position(1,2);
        String position_string = "1,1";

        Assertions.assertTrue(position1.equals(position2));
        Assertions.assertFalse(position1.equals(position3));
        Assertions.assertFalse(position1.equals(position_string));
    }


}

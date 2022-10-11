package com.aor.hero.controller;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Pacman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PacmanControllerTest {

    @Test
    void movePacman(){
        Arena arena = Mockito.mock(Arena.class);
        Pacman pacman = new Pacman(10,10);

        Mockito.when(arena.getPacman()).thenReturn(pacman);

        Mockito.when(arena.isEmpty(Mockito.any(Position.class))).thenReturn(true);
        PacmanController pacmanController =new PacmanController(arena);

        //When
        pacmanController.movePacmanLeft();
        Assertions.assertEquals(9, pacman.getPosition().getX());
        pacmanController.movePacmanUp();
        Assertions.assertEquals(9,pacman.getPosition().getY());
        pacmanController.movePacmanDown();
        Assertions.assertEquals(10, pacman.getPosition().getY());

        //Then
        Mockito.verify(arena,Mockito.times(3)).retrieveFood();
        Mockito.verify(arena,Mockito.times(3)).retrievePowers();
        Mockito.verify(arena,Mockito.times(3)).isGhost();

        //When
        pacman.setPosition(new Position(20,9));
        pacmanController.movePacmanRight();
        Assertions.assertEquals(0, pacman.getPosition().getX());
    }


    @Test
    void doAction_changeSide(){
        Arena arena = Mockito.mock(Arena.class);
        Pacman pacman = Mockito.spy(new Pacman(10,10));
        Mockito.when(arena.isEmpty(Mockito.any(Position.class))).thenReturn(true);
        Mockito.when(arena.getPacman()).thenReturn(pacman);
        PacmanController pacmanController = new PacmanController(arena);


        //When
        pacmanController.doAction(GUI.ACTION.UP);
        pacmanController.changeSide(GUI.ACTION.UP);
        pacmanController.doAction(GUI.ACTION.RIGHT);
        pacmanController.changeSide(GUI.ACTION.RIGHT);
        //Then
        Assertions.assertEquals(11, pacman.getPosition().getX());
        Assertions.assertEquals(9, pacman.getPosition().getY());

        //When
        pacmanController.doAction(GUI.ACTION.DOWN);
        pacmanController.changeSide(GUI.ACTION.DOWN);
        pacmanController.doAction(GUI.ACTION.LEFT);
        pacmanController.changeSide(GUI.ACTION.LEFT);
        //Then
        Assertions.assertEquals(10, pacman.getPosition().getX());
        Assertions.assertEquals(10, pacman.getPosition().getY());

        Mockito.verify(pacman,Mockito.times(4)).setSide(Mockito.anyChar());


        pacmanController.doAction(GUI.ACTION.RIGHT);
        Assertions.assertEquals(11, pacman.getPosition().getX());
        pacmanController.doAction(GUI.ACTION.LEFT);
        Assertions.assertEquals(10, pacman.getPosition().getX());

        Assertions.assertTrue(pacmanController.doAction(GUI.ACTION.RIGHT));
        Mockito.when(arena.isEmpty(Mockito.any(Position.class))).thenReturn(false);
        Assertions.assertFalse(pacmanController.doAction(GUI.ACTION.RIGHT));

    }

    @Test
    void setPowerOFF_getArena(){
        Arena arena = Mockito.mock(Arena.class);
        Pacman pacman = new Pacman(10,10);
        pacman.setPower_status(true);
        Mockito.when(arena.getPacman()).thenReturn(pacman);

        PacmanController pacmanController =new PacmanController(arena);

        //before
        Assertions.assertEquals(true, pacman.isPower_status());

        //when
        pacmanController.setPowerOFF();

        //Then
        Assertions.assertEquals(false, pacman.isPower_status());

        Assertions.assertEquals(arena,pacmanController.getArena());

    }
}

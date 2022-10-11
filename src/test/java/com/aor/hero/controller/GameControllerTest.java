package com.aor.hero.controller;

import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Pacman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameControllerTest {

    @Test
    void teletransport(){
        Pacman pacman = new Pacman(21,9);
        Arena arena = new Arena(200,200);
        PacmanController pacmanController= new PacmanController(arena);
        arena.setPacman(pacman);

        //when
        pacmanController.teletransport(pacman);
        //Then
        Assertions.assertEquals(0,pacman.getPosition().getX());

        //When
        pacman.setPosition(new Position(-1,9));
        pacmanController.teletransport(pacman);
        //Then
        Assertions.assertEquals(20,pacman.getPosition().getX());

    }
}

package com.aor.hero.model.arena;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.*;
import com.aor.hero.viewer.game.ArenaViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ArenaBuilderTest {

    @Test
    void createArena() {

       ArenaBuilder arenaBuilder = Mockito.spy(ArenaBuilder.class);

       //When
       Arena arena = arenaBuilder.createArena();
       arena.setPacman(new Pacman(10,10));

       //Then
       Mockito.verify(arenaBuilder, Mockito.times(1)).createPacman();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createGhosts();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createWalls();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createFoods();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createPowers();

       Assertions.assertTrue(arena.getGhosts() instanceof List<Ghost>);
       Assertions.assertTrue(arena.getWalls() instanceof List<Wall>);
       Assertions.assertTrue(arena.getFoods() instanceof List<Food>);
       Assertions.assertTrue(arena.getPowers() instanceof List<Power>);
       Assertions.assertTrue(arena.getPacman() instanceof Pacman);


    }

    @Test
    void getters() {

        Arena arena = new Arena(50, 75);

        Assertions.assertEquals(50, arena.getWidth());
        Assertions.assertEquals(75, arena.getHeight());
    }

}

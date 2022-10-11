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
       arenaBuilder.createArena();

       //Then
       Mockito.verify(arenaBuilder, Mockito.times(1)).createPacman();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createGhosts();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createWalls();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createFoods();
       Mockito.verify(arenaBuilder, Mockito.times(1)).createPowers();
    }

    @Test
    void getters() {

        Arena arena = new Arena(50, 75);

        Assertions.assertEquals(50, arena.getWidth());
        Assertions.assertEquals(75, arena.getHeight());
    }

}

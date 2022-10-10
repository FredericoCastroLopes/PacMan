package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.*;
import com.aor.hero.viewer.game.ArenaViewer;
import com.aor.hero.viewer.game.Color;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaViewerTest {
    GUI guiMockito = Mockito.mock(GUI.class);
    List<Wall> walls = new ArrayList<>();
    List<Food> foods = new ArrayList<>();
    List<Power> powers = new ArrayList<>();
    List<Ghost> ghosts = new ArrayList<>();
    Pacman pacman = new Pacman(10,15);



    @Test
    void draw() throws IOException {

        walls.add(new Wall(2,0));
        walls.add(new Wall(1,0));

        //given
        Arena arenaMockito = Mockito.mock(Arena.class);
        Mockito.when(arenaMockito.getWalls()).thenReturn(walls);
        Mockito.when(arenaMockito.getFoods()).thenReturn(foods);
        Mockito.when(arenaMockito.getPowers()).thenReturn(powers);
        Mockito.when(arenaMockito.getGhosts()).thenReturn(ghosts);
        Mockito.when(arenaMockito.getPacman()).thenReturn(pacman);
        ArenaViewer arenaViewer = Mockito.spy(new ArenaViewer(guiMockito));

        //when
        arenaViewer.draw(arenaMockito);

        //then
        Mockito.verify(arenaMockito, Mockito.times(3)).getPacman();
        Mockito.verify(arenaMockito, Mockito.times(1)).getFoods();
        Mockito.verify(arenaMockito, Mockito.times(1)).getWalls();
        Mockito.verify(arenaMockito, Mockito.times(1)).getPowers();
        Mockito.verify(arenaMockito, Mockito.times(1)).getGhosts();
        Mockito.verify(guiMockito, Mockito.times(1)).clear();
        Mockito.verify(guiMockito, Mockito.times(1)).refresh();
        Mockito.verify(guiMockito, Mockito.atLeast(2)).drawText(Mockito.any(Position.class), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void close() throws IOException {

        //given
        ArenaViewer arenaViewer = Mockito.spy(new ArenaViewer(guiMockito));

        //when
        arenaViewer.close();

        //then
        Mockito.verify(guiMockito,Mockito.times(1)).close();
    }
}

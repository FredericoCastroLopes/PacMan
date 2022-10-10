package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.elements.*;
import com.aor.hero.viewer.game.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameViewerTest {
    GUI guiMockito = Mockito.mock(GUI.class);

    @Test
    void drawElement_ghost(){
        //given
        Ghost ghostMockito = Mockito.mock(Ghost.class);
        Mockito.when(ghostMockito.getPosition()).thenReturn(new Position(10,8));
        Mockito.when(ghostMockito.isPowerON()).thenReturn(true);
        Mockito.when(ghostMockito.isAlive()).thenReturn(true);
        GhostViewer ghostviewer = Mockito.spy(new GhostViewer());

        //when
        ghostviewer.drawElement(ghostMockito, guiMockito);

        //then
        Mockito.verify(guiMockito, Mockito.times(1)).drawGhost(new Position(10,8),true,true);
    }

    @Test
    void drawElement_food(){
        //given
        Food foodMockito = Mockito.mock(Food.class);
        Mockito.when(foodMockito.getPosition()).thenReturn(new Position(15,9));
        FoodViewer foodViewer = Mockito.spy(new FoodViewer());

        //when
        foodViewer.drawElement(foodMockito, guiMockito);

        //then
        Mockito.verify(guiMockito,Mockito.times(1)).drawFood(new Position(15,9));
    }

    @Test
    void drawElement_pacman(){
        Pacman pacmanMockito = Mockito.mock(Pacman.class);
        Mockito.when(pacmanMockito.getPosition()).thenReturn(new Position(10,15));
        Mockito.when(pacmanMockito.getSide()).thenReturn('p');
        PacmanViewer pacmanViewer = Mockito.spy(new PacmanViewer());

        //when
        pacmanViewer.drawElement(pacmanMockito,guiMockito);

        //then
        Mockito.verify(guiMockito,Mockito.times(1)).drawPacman(new Position(10,15),'p');
    }

    @Test
    void drawElement_power(){
        //given
        Power powerMockito = Mockito.mock(Power.class);
        Mockito.when(powerMockito.getPosition()).thenReturn(new Position(2,1));
        PowerViewer powerViewer = Mockito.spy(new PowerViewer());

        //when
        powerViewer.drawElement(powerMockito, guiMockito);

        //then
        Mockito.verify(guiMockito,Mockito.times(1)).drawPower(new Position(2,1));
    }

    @Test
    void drawElement_wall(){
        //given
        Wall wallMockito = Mockito.mock(Wall.class);
        Mockito.when(wallMockito.getPosition()).thenReturn(new Position(2,0));
        WallViewer wallViewer = Mockito.spy(new WallViewer());

        //when
        wallViewer.drawElement(wallMockito, guiMockito);

        //then
        Mockito.verify(guiMockito,Mockito.times(1)).drawWall(new Position(2,0));

    }
}

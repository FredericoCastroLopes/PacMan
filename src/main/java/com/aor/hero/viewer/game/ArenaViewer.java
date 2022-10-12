package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Element;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final GUI gui;

    public ArenaViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();

        drawElements(arena.getWalls(), new WallViewer());
        drawElements(arena.getFoods(), new FoodViewer());
        drawElements(arena.getPowers(), new PowerViewer());
        drawElements(arena.getGhosts(), new GhostViewer());
        drawElement(arena.getPacman(), new PacmanViewer());


        //LIFE AND SCORE
        int lifes = arena.getPacman().getLifes();
        gui.drawText(new Position(0, 0), "LIFES:" + lifes, "#FF0000");
        for (int i = 0; i<lifes; i++){
            gui.drawText(new Position(i+6,0),"h","#FF0000" );
        }
        gui.drawText(new Position(11,0), "SCORE:" + arena.getPacman().scoretoString(), "#FFD700");

        gui.refresh();
    }

    public   <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    public  <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }

    public void close() throws IOException {
        gui.close();
    }
}

package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.Pacman;

public class PacmanViewer implements ElementViewer<Pacman> {
    @Override
    public void drawElement(Pacman pacman, GUI gui) {
        gui.drawPacman(pacman.getPosition(), pacman.getSide());
    }
}

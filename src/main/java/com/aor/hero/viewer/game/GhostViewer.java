package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.Ghost;

public class GhostViewer implements ElementViewer<Ghost> {
    @Override
    public void drawElement(Ghost ghost, GUI gui) {
        gui.drawGhost(ghost.getPosition());
    }
}

package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.Element;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, GUI gui);
}

package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.Power;

public class PowerViewer implements ElementViewer<Power>{

    @Override
    public void drawElement(Power power, GUI gui) {gui.drawPower(power.getPosition());}
}

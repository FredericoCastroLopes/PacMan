package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.elements.Food;

public class FoodViewer implements ElementViewer<Food>{
    @Override
    public void drawElement(Food food, GUI gui) {
        gui.drawFood(food.getPosition());
    }
}

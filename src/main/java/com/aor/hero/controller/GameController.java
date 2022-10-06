package com.aor.hero.controller;

import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Element;

public abstract class GameController {
    protected final Arena arena;

    public GameController(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }

    protected void teletransport(Element element){
        if (element.getPosition().getX() == 21 && element.getPosition().getY() == 9){
            element.setPosition(new Position(0,9));
        } else if (element.getPosition().getX() == -1 && element.getPosition().getY() == 9) {
            element.setPosition(new Position(20,9));
        }
    }
}

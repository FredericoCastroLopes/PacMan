package com.aor.hero.controller;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Element;

public class PacmanController extends GameController {
    public PacmanController(Arena arena) {
        super(arena);
    }

    public void moveHeroLeft() {
        movePacman(arena.getPacman().getPosition().getLeft());
    }

    public void moveHeroRight() {
        movePacman(arena.getPacman().getPosition().getRight());
    }

    public void moveHeroUp() {
        movePacman(arena.getPacman().getPosition().getUp());
    }

    public void moveHeroDown() {
        movePacman(arena.getPacman().getPosition().getDown());
    }

    private void movePacman(Position position) {
        if (arena.isEmpty(position)) {
            arena.getPacman().setPosition(position);

            //Teletransport
            teletransport(arena.getPacman());

            //Remove food and decrease energy
            arena.retrieveFood();
            if (arena.isGhost(position)) arena.getPacman().decreaseEnergy();
        }
    }

    public boolean doAction(GUI.ACTION action) {
        Position initial = arena.getPacman().getPosition();
        if (action == GUI.ACTION.UP) moveHeroUp();
        if (action == GUI.ACTION.RIGHT) moveHeroRight();
        if (action == GUI.ACTION.DOWN) moveHeroDown();
        if (action == GUI.ACTION.LEFT) moveHeroLeft();
        Position after = arena.getPacman().getPosition();
        if(initial.equals(after)){
           return false;
        }else{
            return true;
        }
    }

}

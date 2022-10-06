package com.aor.hero.controller;

import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Ghost;

public class GhostController extends GameController {
    public GhostController(Arena arena) {
        super(arena);
    }

    public void moveMonsters() {
        for (Ghost ghost : arena.getGhosts())
            moveMonster(ghost, ghost.getPosition().getRandomNeighbour());
    }

    private void moveMonster(Ghost ghost, Position position) {
        if (arena.isEmpty(position)) {
            ghost.setPosition(position);
            if (arena.getPacman().getPosition().equals(position))
                arena.getPacman().decreaseEnergy();
        }
    }
}

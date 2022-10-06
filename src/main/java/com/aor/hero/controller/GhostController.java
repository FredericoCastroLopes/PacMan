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
            moveMonster(ghost);
    }

    private void moveMonster(Ghost ghost) {
        Position position;
        do {
            position = ghost.getPosition().getRandomNeighbour();
        }while (!arena.isEmpty(position));
            ghost.setPosition(position);
            teletransport(ghost);
            if (arena.getPacman().getPosition().equals(position))
                arena.getPacman().decreaseEnergy();
        }
}

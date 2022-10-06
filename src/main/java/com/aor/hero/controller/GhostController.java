package com.aor.hero.controller;

import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Ghost;

public class GhostController extends GameController {
    public GhostController(Arena arena) {
        super(arena);
    }

    public void moveGhosts() {
        for (Ghost ghost : arena.getGhosts())
            moveGhost(ghost);
    }

    private void moveGhost(Ghost ghost) {
        Position new_position;
        Position pacman_pos = arena.getPacman().getPosition();
        Position ghost_pos = ghost.getPosition();
        int dist_x = pacman_pos.getX() - ghost.getPosition().getX();
        int dist_y = pacman_pos.getY() - ghost.getPosition().getY();


        //Inside the box exceptions
        if(ghost.getPosition().equals(new Position(10,8)) || ghost.getPosition().equals(new Position(10,9))){
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() - 1);
            ghost.setPosition(new_position);
        }else if(ghost.getPosition().equals(new Position(11,8))){
            new_position = new Position(ghost.getPosition().getX() - 1, ghost.getPosition().getY());
            ghost.setPosition(new_position);
        }else if(ghost.getPosition().equals(new Position(9,8))) {
            new_position = new Position(ghost.getPosition().getX() + 1, ghost.getPosition().getY());
            ghost.setPosition(new_position);


        // Follow the pacman
        } else if (Math.abs(dist_y) > Math.abs(dist_x) && dist_y != 0) {
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() + (dist_y / Math.abs(dist_y)));
            if (arena.isEmpty(new_position) && !(new_position.equals(ghost.getLast_position()))) {
                ghost.setPosition(new_position);
                teletransport(ghost);
            }
        }else if (Math.abs(dist_x) > Math.abs(dist_y) && dist_x != 0) {
                new_position = new Position(ghost.getPosition().getX() + (dist_x / Math.abs(dist_x)), ghost.getPosition().getY());
                if (arena.isEmpty(new_position) && !(new_position.equals(ghost.getLast_position()))) {
                    ghost.setPosition(new_position);
                    teletransport(ghost);
                }
            }


        //Random move in case there is no better option
        if (ghost.getPosition().equals(ghost_pos)) {
            do {
                new_position = ghost.getPosition().getRandomNeighbour();

            } while (!arena.isEmpty(new_position) || new_position.equals(ghost.getLast_position()));
            ghost.setPosition(new_position);
            teletransport(ghost);
        }


        //Lose lifes
        if (arena.getPacman().getPosition().equals(ghost.getPosition())) {
            arena.getPacman().decreaseEnergy();
        }
    }
}

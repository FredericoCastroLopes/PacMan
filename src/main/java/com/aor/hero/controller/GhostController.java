package com.aor.hero.controller;

import com.aor.hero.controller.Sound.MusicManager;
import com.aor.hero.controller.Sound.Sounds;
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
        Position chasing_pos = chasingPosition(ghost);
        Position ghost_pos = ghost.getPosition();

        int dist_x = chasing_pos.getX() - ghost.getPosition().getX();
        int dist_y = chasing_pos.getY() - ghost.getPosition().getY();


            //Inside the box exceptions
        if (ghost.isAlive() && (ghost.getPosition().equals(new Position(10, 8)) || ghost.getPosition().equals(new Position(10, 9)))) {
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() - 1);
            ghost.setPosition(new_position);
        } else if (ghost.isAlive() && (ghost.getPosition().equals(new Position(11, 8)))) {
            new_position = new Position(ghost.getPosition().getX() - 1, ghost.getPosition().getY());
            ghost.setPosition(new_position);
        } else if (ghost.isAlive() && (ghost.getPosition().equals(new Position(9, 8)))) {
            new_position = new Position(ghost.getPosition().getX() + 1, ghost.getPosition().getY());
            ghost.setPosition(new_position);


            //Deads exceptions
        } else if (!ghost.isAlive() && (ghost.getPosition().equals(new Position(15,9)))) {
                ghost.setPosition(new Position(ghost.getPosition().getX()-1, ghost.getPosition().getY()));

        } else if (!ghost.isAlive() && (ghost.getPosition().equals(new Position(5,9)))) {
            ghost.setPosition(new Position(ghost.getPosition().getX()+1, ghost.getPosition().getY()));

        } else if (!ghost.isAlive() && ghost.getPosition().getY()>=9) {
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() - 1);
            if (arena.isEmpty(new_position)) {
                ghost.setPosition(new_position);
            }
        } else if (!ghost.isAlive() && (ghost.getPosition().getY() == 2 || ghost.getPosition().getY() == 4)) {
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() + 1);
            if (arena.isEmpty(new_position)) {
                ghost.setPosition(new_position);
            }


            // Follow the pacman
        } else if (Math.abs(dist_y) > Math.abs(dist_x) && dist_y != 0 && !ghost.isPowerON()) {
            new_position = new Position(ghost.getPosition().getX(), ghost.getPosition().getY() + (dist_y / Math.abs(dist_y)));
            if (arena.isEmpty(new_position) && !(new_position.equals(ghost.getLast_position()))) {
                ghost.setPosition(new_position);
                teletransport(ghost);
            }
        } else if (Math.abs(dist_x) > Math.abs(dist_y) && dist_x != 0 && !ghost.isPowerON()) {
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

        //Lose lifes & Kill ghosts
        if (arena.getPacman().getPosition().equals(ghost.getPosition())) {
            LoseLifes_KillGhosts(ghost);
        }

        //Reborn
        rebornGhost(ghost);
    }

    protected void LoseLifes_KillGhosts(Ghost ghost){
            if (arena.getPacman().isPower_status()) {
                ghost.setAlive(false);
                if (!MusicManager.getInstance().isPlaying(Sounds.KILL)){
                    MusicManager.getInstance().start(Sounds.KILL);
                }
            } else {
                arena.getPacman().decreaseLifes();
                if (!MusicManager.getInstance().isPlaying(Sounds.LOSELIFE)){
                    MusicManager.getInstance().start(Sounds.LOSELIFE);
                }
            }
        }

    protected void rebornGhost(Ghost ghost){
        if(ghost.getPosition().equals(new Position(10,8))){
            ghost.setAlive(true);
        }
    }

    private Position chasingPosition(Ghost ghost){
        Position chasing_pos;
        if(ghost.isAlive()){
            chasing_pos = arena.getPacman().getPosition();
        }else {
            chasing_pos = new Position(10,8);
        }
        return chasing_pos;
    }
}

package com.aor.hero.controller;

import com.aor.hero.controller.Sound.MusicManager;
import com.aor.hero.controller.Sound.Sounds;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;

public class PacmanController extends GameController {
    public PacmanController(Arena arena) {
        super(arena);
    }

    public void movePacmanLeft() {
        movePacman(arena.getPacman().getPosition().getLeft());
    }

    public void movePacmanRight() {
        movePacman(arena.getPacman().getPosition().getRight());
    }

    public void movePacmanUp() {movePacman(arena.getPacman().getPosition().getUp());}

    public void movePacmanDown() {
        movePacman(arena.getPacman().getPosition().getDown());
    }

    private void movePacman(Position position) {
        if (arena.isEmpty(position)) {
            arena.getPacman().setPosition(position);

            //Teletransport
            teletransport(arena.getPacman());

            //Remove food and decrease energy
            arena.retrieveFood();
            arena.retrievePowers();
            arena.isGhost();

            if (!MusicManager.getInstance().isPlaying(Sounds.PACMAN)){
                MusicManager.getInstance().start(Sounds.PACMAN);
            }
        }
        else {
            MusicManager.getInstance().stop(Sounds.PACMAN);
        }
    }

    public boolean doAction(GUI.ACTION action) {
        Position initial = arena.getPacman().getPosition();
        if (action == GUI.ACTION.UP) movePacmanUp();
        if (action == GUI.ACTION.RIGHT) movePacmanRight();
        if (action == GUI.ACTION.DOWN) movePacmanDown();
        if (action == GUI.ACTION.LEFT) movePacmanLeft();
        Position after = arena.getPacman().getPosition();
        if(initial.equals(after)){
           return false;
        }else{
            return true;
        }
    }

    public void changeSide(GUI.ACTION action){
        if (action == GUI.ACTION.UP) arena.getPacman().setSide('x');
        if (action == GUI.ACTION.RIGHT) arena.getPacman().setSide('p');
        if (action == GUI.ACTION.DOWN) arena.getPacman().setSide('z');
        if (action == GUI.ACTION.LEFT) arena.getPacman().setSide('y');
    }


    public void setPowerOFF(){
        arena.getPacman().setPower_status(false);
    }

}

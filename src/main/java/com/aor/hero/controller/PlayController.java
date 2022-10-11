package com.aor.hero.controller;

import com.aor.hero.gui.GUI;
import com.aor.hero.gui.LanternaGUI;
import com.aor.hero.model.Menu.states.ApplicationState;
import com.aor.hero.model.Menu.states.GameOverController;
import com.aor.hero.model.Menu.states.StateController;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.arena.LoaderArenaBuilder;
import com.aor.hero.viewer.game.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlayController implements StateController {

    private int score;
    private final Controller context;

    public PlayController(Controller context){
        this.context = context;
    }


    @Override
    public void run() throws FontFormatException, IOException, URISyntaxException {

        Arena arena = new LoaderArenaBuilder(1).createArena();
        GUI gui = new LanternaGUI(arena.getWidth(), arena.getHeight());
        ArenaViewer viewer = new ArenaViewer(gui);
        ArenaController controller = new ArenaController(arena, viewer, gui);

        controller.start();
        this.score = controller.arena.getPacman().getScore();
        nextState();
    }


    @Override
    public void nextState() {
        context.changeState(ApplicationState.GameOver);
        ((GameOverController)context.getStateControler()).setScore(this.score);
    }
}

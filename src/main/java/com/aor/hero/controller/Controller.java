package com.aor.hero.controller;


import com.aor.hero.controller.Sound.MusicManager;
import com.aor.hero.controller.Sound.Sounds;
import com.aor.hero.model.Menu.states.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {

    private StateController stateControler;
    private ApplicationState applicationState;

    private GameController gameController;

    public Controller(){
        changeState(ApplicationState.Menu);
    }

    public void run() throws IOException, URISyntaxException, FontFormatException {
        while (getStateControler() != null)
            getStateControler().run();
    }


    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state){
        applicationState = state;
        switch (state){
            case Game -> stateControler= new PlayController(this);
            case Menu -> {
                if (!MusicManager.getInstance().isPlaying(Sounds.SOUNDTRACK)) {
                    MusicManager.getInstance().stopAll();
                    MusicManager.getInstance().start(Sounds.SOUNDTRACK);
                }
                stateControler = new MenuController(this);
            }
            case LeaderBoard -> stateControler = new LeaderboardController(this);
            case Instructions -> stateControler = new InstructionsController(this);
            case GameOver -> {
                MusicManager.getInstance().stopAll();
                MusicManager.getInstance().start(Sounds.GAMEOVER);
                stateControler = new GameOverController(this);
            }
            case Exit-> {
                MusicManager.getInstance().stopAll();
                stateControler=null;
            }
        }
    }

    public StateController getStateControler() {
        return stateControler;
    }
}
package com.aor.hero.model.Menu.states;

import com.aor.hero.controller.Controller;
import com.aor.hero.viewer.screens.ScreenView;
import com.aor.hero.viewer.screens.LeaderboardScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LeaderboardController implements StateController, KeyListener {
    private final Controller context;
    private ScreenView screenView;

    public LeaderboardController(Controller context) {
        this.context = context;
        screenView = new LeaderboardScreen();
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);


        while (context.getApplicationState() == ApplicationState.LeaderBoard) {
            getScreenView().draw();
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    @Override
    public void nextState() {
        context.changeState(ApplicationState.Menu);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nextState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setScreenView(ScreenView screenView) {
        this.screenView = screenView;
    }
}
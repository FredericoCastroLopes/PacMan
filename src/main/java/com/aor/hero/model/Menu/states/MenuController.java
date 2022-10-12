package com.aor.hero.model.Menu.states;

import com.aor.hero.controller.Controller;
import com.aor.hero.viewer.screens.ScreenView;
import com.aor.hero.viewer.screens.MenuScreen;
import com.aor.hero.model.Menu.Menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MenuController implements StateController, KeyListener {

    private final Controller context;
    private ScreenView screenView;
    private final Menu menu;

    public MenuController(Controller context){
        this.context = context;
        this.menu = new Menu();
        this.screenView = new MenuScreen(menu);
    }

    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);

        while (context.getApplicationState() == ApplicationState.Menu) {
            getScreenView().draw();

            if(getMenu().isChoosed())
                nextState();
        }


        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    @Override
    public void nextState(){
        switch (getMenu().getSelected()){
            case Play -> context.changeState(ApplicationState.Game);
            case LeaderBoard -> context.changeState(ApplicationState.LeaderBoard);
            case Instructions -> context.changeState(ApplicationState.Instructions);
            case Exit -> context.changeState(ApplicationState.Exit);
        }
    }

    public Menu getMenu() {
        return menu;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN -> getMenu().selectNext();
            case KeyEvent.VK_UP -> getMenu().selectprevious();
            case KeyEvent.VK_ENTER -> getMenu().choose();
            case KeyEvent.VK_ESCAPE -> context.changeState(ApplicationState.Exit);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setScreenView(ScreenView screenView) {
        this.screenView = screenView;
    }
}

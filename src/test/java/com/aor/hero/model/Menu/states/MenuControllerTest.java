package com.aor.hero.model.Menu.states;

import com.aor.hero.controller.Controller;
import com.aor.hero.model.Menu.Menu;
import com.aor.hero.model.Menu.MenuItem;
import com.aor.hero.viewer.screens.ScreenView;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuControllerTest {
    MenuController menuControllerSpy;
    Controller context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;
    Menu menu;

    @BeforeEach
    void initGameController(){
        // create context
        context = Mockito.mock(Controller.class);

        // create MenuController
        menuControllerSpy = Mockito.spy(new MenuController(context));

        // create menuMoCK
        menu = Mockito.mock(Menu.class);
        Mockito.when(menuControllerSpy.getMenu()).thenReturn(menu);

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        menuControllerSpy.setScreenView(screenViewMock);

    }

    @Test
    void processKeyArrowDown(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);

        // when
        menuControllerSpy.keyPressed(e);
        // then
        Mockito.verify(menu, Mockito.times(1)).selectNext();
    }

    @Test
    void processKeyArrowUp(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(menu, Mockito.times(1)).selectprevious();
    }

    @Test
    void processKeyEnter(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ENTER, '\n');

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(menu, Mockito.times(1)).choose();
    }

    @Test
    void processKeyEscape(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void startRun() throws IOException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(menuControllerSpy);

    }

    @Test
    void endRun() throws IOException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(menuControllerSpy);

    }

    @Test
    void testRunWhileStopGame() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Game);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(2)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Exit);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(1)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopLeaderboard() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Game);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(2)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileChoosed_1true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void testRunWhileChoosed_2true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(2)).nextState();
    }

    @Test
    void testRunWhileChoosed_0true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,false,false);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(0)).nextState();
    }

    @Test
    void choosePlay(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Play);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Game);
    }

    @Test
    void chooseLeaderBoard(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.LeaderBoard);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.LeaderBoard);
    }
    @Test
    void chooseInstrucitons(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Instructions);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Instructions);
    }

    @Test
    void chooseExit(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Exit);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void testRunStop() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(0)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(0)).draw();

    }
}

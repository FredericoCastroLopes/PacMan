package com.aor.hero.controller;

import com.aor.hero.controller.Sound.MusicManager;
import com.aor.hero.controller.Sound.Sounds;
import com.aor.hero.model.Menu.states.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    Controller controllerSpy;
    @BeforeEach
    void initController(){

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            Controller controller = new Controller();
            controllerSpy = Mockito.spy(controller);
        }
    }
    @Test
    void runState() throws IOException, URISyntaxException, FontFormatException {

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            // given
            StateController stateController = Mockito.mock(StateController.class);
            StateController stateController2 = Mockito.mock(StateController.class);

            Mockito.when(controllerSpy.getStateControler()).thenReturn(stateController, stateController, stateController2, stateController2, null);

            // when
            controllerSpy.run();

            // then
            Mockito.verify(stateController, Mockito.times(1)).run();
            Mockito.verify(stateController2, Mockito.only()).run();
            Mockito.verify(controllerSpy, Mockito.times(5)).getStateControler();
        }
    }

    @Test
    void initialState(){

        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
    }

    @Test
    void changeStateGame(){

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Game);

            // then
            assertTrue(controllerSpy.getStateControler() instanceof PlayController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.Game);
        }
    }


    @Test
    void changeStateLeaderboard(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.LeaderBoard);

            // then
            assertTrue(controllerSpy.getStateControler() instanceof LeaderboardController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.LeaderBoard);
        }
    }
    @Test
    void changeStateGameOver(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.GameOver);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            Mockito.verify(manager, Mockito.times(1)).start(Sounds.GAMEOVER);
            assertTrue(controllerSpy.getStateControler() instanceof GameOverController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.GameOver);
        }
    }

    @Test
    void changeStateInstructions(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Instructions);

            // then
            assertTrue(controllerSpy.getStateControler() instanceof InstructionsController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.Instructions);
        }
    }

    @Test
    void changeStateExit(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Exit);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            assertNull(controllerSpy.getStateControler());
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.Exit);
        }
    }

    @Test
    void changeStateMenuIsPlaying(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            Mockito.when(manager.isPlaying(Sounds.SOUNDTRACK)).thenReturn(true);
            controllerSpy.changeState(ApplicationState.Menu);

            // then
            Mockito.verify(manager, Mockito.never()).stopAll();
            Mockito.verify(manager, Mockito.never()).start(Sounds.SOUNDTRACK);
            assertTrue(controllerSpy.getStateControler() instanceof MenuController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
        }
    }

    @Test
    void changeStateMenuIsNotPlaying(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            Mockito.when(manager.isPlaying(Sounds.SOUNDTRACK)).thenReturn(false);
            controllerSpy.changeState(ApplicationState.Menu);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            Mockito.verify(manager, Mockito.times(1)).start(Sounds.SOUNDTRACK);
            assertTrue(controllerSpy.getStateControler() instanceof MenuController);
            assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
        }
    }
}

package com.aor.hero.controller;

import com.aor.hero.model.Menu.states.ApplicationState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PlayControllerTest {

    PlayController playController;
    Controller context;

    @Test
    void nextState(){
        context = Mockito.spy(new Controller());
        playController = Mockito.spy(new PlayController(context));

        //When
        playController.nextState();

        //Then
        Mockito.verify(context,times(1)).changeState(any(ApplicationState.class));
        Mockito.verify(context,times(1)).getStateControler();
    }
}

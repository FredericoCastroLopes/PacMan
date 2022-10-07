package com.aor.hero.model.Menu.states;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface StateController {
    void run() throws IOException, FontFormatException, URISyntaxException;

    void nextState();

}

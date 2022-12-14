package com.aor.hero.gui;

import com.aor.hero.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawPacman(Position position, char side);

    void drawWall(Position position);

    void drawGhost(Position position, boolean isAlive, boolean isPowerON);

    void drawFood(Position position);

    void drawPower(Position position);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT}

}

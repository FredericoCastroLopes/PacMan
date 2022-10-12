package com.aor.hero.viewer.screens;

import com.aor.hero.viewer.game.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class View {
    protected int charWidth;
    protected int charHeight;

    protected TextGraphics graphics;
    public View(int charWidth , int charHeight){
        this.charWidth = charWidth;
        this.charHeight = charHeight;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw() throws IOException;

    public void setBackgroundColor(Color color){
        getGraphics().setBackgroundColor(color.getColor());
    }


    public void setColor(char color){
        Color c = Color.getColor(color);
        if (c!=null)
            setBackgroundColor(c);
    }
}

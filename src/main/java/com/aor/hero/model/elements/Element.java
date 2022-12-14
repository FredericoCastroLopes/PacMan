package com.aor.hero.model.elements;

import com.aor.hero.model.Position;

public abstract class Element {

    protected Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

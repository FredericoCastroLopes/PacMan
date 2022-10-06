package com.aor.hero.model.elements;

import com.aor.hero.model.Position;

public class Ghost extends Element {

    private Position last_position;
    public Ghost(int x, int y)
    {
        super(x, y);
        this.last_position = getPosition();
    }

    @Override
    public void setPosition(Position position){
        this.last_position = this.position;
        this.position = position;
    }

    public Position getLast_position() {
        return last_position;
    }

}

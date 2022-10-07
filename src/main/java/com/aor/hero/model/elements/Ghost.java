package com.aor.hero.model.elements;

import com.aor.hero.model.Position;

public class Ghost extends Element {


    private boolean isPowerON;
    private boolean alive;
    private Position last_position;
    public Ghost(int x, int y)
    {
        super(x, y);
        this.last_position = getPosition();
        this.isPowerON = false;
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isPowerON() {
        return isPowerON;
    }

    public void setPowerON(boolean powerON) {
        isPowerON = powerON;
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

package com.aor.hero.model.elements;

public class Pacman extends Element {
    private int energy;

    private char side;

    public Pacman(int x, int y) {
        super(x, y);
        this.energy = 10;
        this.side = 'p';
    }

    public void decreaseEnergy() {
        this.energy--;
    }

    public int getEnergy() {
        return energy;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }
}

package com.aor.hero.model.elements;

public class Pacman extends Element {
    private int energy;

    public Pacman(int x, int y) {
        super(x, y);
        this.energy = 10;
    }

    public void decreaseEnergy() {
        this.energy--;
    }

    public int getEnergy() {
        return energy;
    }
}

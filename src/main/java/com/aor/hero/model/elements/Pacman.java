package com.aor.hero.model.elements;

public class Pacman extends Element {
    private int lifes;

    private int score;

    private char side;

    private boolean power_status;

    public Pacman(int x, int y) {
        super(x, y);
        this.lifes = 3;
        this.score = 0;
        this.side = 'p';
        this.power_status = false;
    }

    public void decreaseLifes() {
        this.lifes--;
    }

    public int getLifes() {
        return lifes;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public boolean isPower_status() {
        return power_status;
    }

    public void setPower_status(boolean power_status) {
        this.power_status = power_status;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score = this.score + score;
    }

    public String scoretoString() {
        String score = Integer.toString(this.score);
        while (score.length() < 4) {
            score = "0" + score;
        }
        return score;
    }
}

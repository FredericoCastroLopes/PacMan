package com.aor.hero.model.elements;

import com.aor.hero.controller.Sound.MusicManager;
import com.aor.hero.controller.Sound.Sounds;

public class Pacman extends Element {
    private int lifes;
    private int score;
    private char side;
    private boolean power_status;

    private int extra_powers;

    public Pacman(int x, int y) {
        super(x, y);
        this.lifes = 3;
        this.score = 0;
        this.side = 'p';
        this.power_status = false;
        extra_powers = 0;
    }

    public void decreaseLifes() {
        this.lifes--;
        if (!MusicManager.getInstance().isPlaying(Sounds.LOSELIFE)){
            MusicManager.getInstance().start(Sounds.LOSELIFE);
        }
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

    public void setPower_status(boolean new_power_status) {
        if(new_power_status && isPower_status()){
            extra_powers ++;
        }else
        this.power_status = new_power_status;
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

    public int getExtra_powers() {
        return extra_powers;
    }

    public void reduceExtra_powers() {
        this.extra_powers = extra_powers - 1;
    }
}

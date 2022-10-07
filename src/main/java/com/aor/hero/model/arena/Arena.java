package com.aor.hero.model.arena;

import com.aor.hero.model.Position;
import com.aor.hero.model.elements.*;

import java.util.List;

public class Arena {

    private final int width;
    private final int height;

    private Pacman pacman;
    private List<Ghost> ghosts;

    private List<Wall> walls;

    private List<Food> foods;

    private List<Power> powers;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pacman getPacman() {return pacman;}

    public void setPacman(Pacman pacman) {this.pacman = pacman;}

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void setGhosts(List<Ghost> ghosts) {this.ghosts = ghosts;}

    public List<Wall> getWalls() {return walls;}

    public void setWalls(List<Wall> walls) {this.walls = walls;}

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Power> getPowers() {return powers;}

    public void setPowers(List<Power> powers) {this.powers = powers;}

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public void isGhost() {
        for (Ghost ghost : ghosts)
            if (ghost.getPosition().equals(pacman.getPosition())){
                if (pacman.isPower_status()){
                    ghost.setAlive(false);
                    break;
                }else {
                    pacman.decreaseLifes();
                }
            }
    }

    public void retrieveFood(){
        for (int i = 0; i < this.foods.size(); i++) {
            if (pacman.getPosition().equals(foods.get(i).getPosition())) {
                this.foods.remove(i);
                pacman.increaseScore(10);
            }
        }
    }

    public void retrievePowers(){
        for (int i = 0; i < this.powers.size(); i++) {
            if (pacman.getPosition().equals(powers.get(i).getPosition())) {
                this.powers.remove(i);
                pacman.increaseScore(100);
                pacman.setPower_status(true);
                notifyGhosts(true);
            }
        }
    }

    public void notifyGhosts(boolean power){
        for (Ghost ghost : ghosts){
            ghost.setPowerON(power);
        }
    }

}

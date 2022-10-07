package com.aor.hero.model.arena;

import com.aor.hero.model.Position;
import com.aor.hero.model.elements.Food;
import com.aor.hero.model.elements.Pacman;
import com.aor.hero.model.elements.Ghost;
import com.aor.hero.model.elements.Wall;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private Pacman pacman;
    private List<Ghost> ghosts;
    private List<Wall> walls;

    private List<Food> foods;

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

    public Pacman getPacman() {

        return pacman;
    }

    public void setPacman(Pacman pacman) {

        this.pacman = pacman;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void setGhosts(List<Ghost> ghosts) {

        this.ghosts = ghosts;
    }

    public List<Wall> getWalls() {

        return walls;
    }

    public void setWalls(List<Wall> walls) {

        this.walls = walls;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isGhost(Position position) {
        for (Ghost ghost : ghosts)
            if (ghost.getPosition().equals(position))
                return true;
        return false;
    }

    public void retrieveFood(){
        for (int i = 0; i < this.foods.size(); i++) {
            if (pacman.getPosition().equals(foods.get(i).getPosition())) {
                this.foods.remove(i);
                pacman.increaseScore(10);
            }
        }
    }
}

package com.aor.hero.model.arena;

import com.aor.hero.model.elements.*;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setPacman(createPacman());
        arena.setGhosts(createGhosts());
        arena.setWalls(createWalls());
        arena.setFoods(createFoods());
        arena.setPowers(createPowers());

        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<Food> createFoods();

    protected abstract List<Power> createPowers();

    protected abstract List<Ghost> createGhosts();

    protected abstract Pacman createPacman();
}

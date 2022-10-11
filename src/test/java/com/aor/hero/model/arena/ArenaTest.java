package com.aor.hero.model.arena;

import com.aor.hero.model.Position;
import com.aor.hero.model.elements.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ArenaTest {

    Arena arena = new Arena(50, 75);
    List<Wall> walls = new ArrayList<>();
    List<Food> foods = new ArrayList<>();
    List<Power> powers = new ArrayList<>();
    List<Ghost> ghosts = new ArrayList<>();
    Pacman pacman = new Pacman(10, 15);


    @Test
    void getters_setters() {

        Assertions.assertEquals(50, arena.getWidth());
        Assertions.assertEquals(75, arena.getHeight());

        arena.setPacman(pacman);
        Assertions.assertEquals(pacman, arena.getPacman());

        arena.setGhosts(ghosts);
        Assertions.assertEquals(ghosts, arena.getGhosts());

        arena.setWalls(walls);
        Assertions.assertEquals(walls, arena.getWalls());

        arena.setFoods(foods);
        Assertions.assertEquals(foods, arena.getFoods());

        arena.setPowers(powers);
        Assertions.assertEquals(powers, arena.getPowers());
    }
}

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

    @Test
    void isEmptyFALSE() {

        Arena arena = new Arena(50, 75);

        Position position = new Position(10,15);
        walls.add(new Wall(10,15));
        arena.setWalls(walls);

        boolean isEmpty = arena.isEmpty(position);

        Assertions.assertEquals(false, isEmpty);
    }

    @Test
    void isEmptyTRUE() {

        Arena arena = new Arena(50, 75);

        Position position = new Position(10,17);
        walls.add(new Wall(10,15));
        arena.setWalls(walls);

        boolean isEmpty = arena.isEmpty(position);

        Assertions.assertEquals(true, isEmpty);
    }

    @Test
    void retrieveFood() {

        Arena arena = new Arena(50,75);
        arena.setPacman(new Pacman(10,15));

        foods.add(new Food(10,15));
        foods.add(new Food(17,15));
        arena.setFoods(foods);

        arena.retrieveFood();

        Assertions.assertEquals(1, foods.size());
        Assertions.assertEquals(10,arena.getPacman().getScore());
    }

    @Test
    void retrievePowers() {

        Arena arena = new Arena(50,75);
        arena.setPacman(new Pacman(10,15));

        powers.add(new Power(10,15));
        powers.add(new Power(18,20));
        arena.setPowers(powers);

        ghosts.add(new Ghost(10,10));
        arena.setGhosts(ghosts);


        arena.retrievePowers();

        Assertions.assertEquals(1, powers.size());
        Assertions.assertEquals(100, arena.getPacman().getScore());
        Assertions.assertEquals(true, arena.getPacman().isPower_status());
        Assertions.assertEquals(true, arena.getGhosts().get(0).isPowerON());
    }
}

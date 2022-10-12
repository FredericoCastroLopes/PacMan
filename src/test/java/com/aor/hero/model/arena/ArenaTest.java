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

    List<Wall> walls = new ArrayList<>();
    List<Food> foods = new ArrayList<>();
    List<Power> powers = new ArrayList<>();
    List<Ghost> ghosts = new ArrayList<>();
    Pacman pacman = new Pacman(10, 15);
    Arena arena = new Arena(50, 75);



    @Test
    void getters_setters() {
        Assertions.assertEquals(50, arena.getWidth());
        Assertions.assertEquals(75, arena.getHeight());
        ghosts.add(new Ghost(1,1));
        walls.add(new Wall(1,1));
        foods.add(new Food(1,1));
        powers.add(new Power(1,1));

        arena.setPacman(pacman);
        Assertions.assertEquals(pacman, arena.getPacman());

        arena.setGhosts(ghosts);
        Assertions.assertEquals(ghosts.size(), arena.getGhosts().size());

        arena.setWalls(walls);
        Assertions.assertEquals(walls.size(), arena.getWalls().size());

        arena.setFoods(foods);
        Assertions.assertEquals(foods.size(), arena.getFoods().size());

        arena.setPowers(powers);
        Assertions.assertEquals(powers.size(), arena.getPowers().size());

        ghosts.clear();
        walls.clear();
        foods.clear();
        powers.clear();

    }

    @Test
    void isEmpty() {
        arena.setPacman(pacman);
        Position position = new Position(10,16);
        walls.add(new Wall(10,16));
        arena.setWalls(walls);

        boolean isEmpty = arena.isEmpty(position);

        Assertions.assertEquals(false, isEmpty);

        Position position2 = new Position(10,17);
        boolean isEmpty2 = arena.isEmpty(position2);

        Assertions.assertEquals(true, isEmpty2);
    }

    @Test
    void retrieveFood() {
        arena.setPacman(pacman);

        foods.add(new Food(10,15));
        foods.add(new Food(17,15));
        foods.add(new Food(18,15));
        arena.setFoods(foods);

        arena.retrieveFood();

        Assertions.assertEquals(2, foods.size());
        Assertions.assertEquals(10,arena.getPacman().getScore());
    }

    @Test
    void retrievePowers() {
        arena.setPacman(pacman);

        powers.add(new Power(10,15));
        powers.add(new Power(18,20));
        powers.add(new Power(19,20));
        arena.setPowers(powers);

        ghosts.add(new Ghost(10,10));
        arena.setGhosts(ghosts);


        arena.retrievePowers();

        Assertions.assertEquals(2, powers.size());
        Assertions.assertEquals(100, arena.getPacman().getScore());
        Assertions.assertEquals(true, arena.getPacman().isPower_status());
        Assertions.assertEquals(true, arena.getGhosts().get(0).isPowerON());
    }

    @Test
    void isGhost(){
        ghosts.add(new Ghost(10,15));
        ghosts.add(new Ghost(10,16));
        arena.setPacman(pacman);
        arena.getPacman().setPower_status(true);

        arena.setGhosts(ghosts);

        //When
        arena.isGhost();

        //Then
        Assertions.assertFalse(arena.getGhosts().get(0).isAlive());

        //When
        arena.getPacman().setPower_status(false);
        arena.getPacman().setPosition(new Position(10,16));
        arena.isGhost();

        //Then
        Assertions.assertEquals(2,arena.getPacman().getLifes());
    }
}

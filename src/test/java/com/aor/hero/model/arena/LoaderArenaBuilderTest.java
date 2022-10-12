package com.aor.hero.model.arena;

import com.aor.hero.model.elements.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderArenaBuilderTest {

    @Test
    void getWidth() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        Assertions.assertEquals(21, loaderArenaBuilder.getWidth());
    }

    @Test
    void getHeight() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        Assertions.assertEquals(21, loaderArenaBuilder.getHeight());
    }

    @Test
    void createWalls() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        List<Wall> walls = loaderArenaBuilder.createWalls();
        Assertions.assertEquals(214, walls.size());

        Assertions.assertEquals(1, walls.get(0).getPosition().getX());
        Assertions.assertEquals(0, walls.get(0).getPosition().getY());
        Assertions.assertEquals(19, walls.get(213).getPosition().getX());
        Assertions.assertEquals(20, walls.get(213).getPosition().getY());
    }

    @Test
    void createFoods() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        List<Food> foods = loaderArenaBuilder.createFoods();
        Assertions.assertEquals(146, foods.size());

        Assertions.assertEquals(3, foods.get(0).getPosition().getX());
        Assertions.assertEquals(1, foods.get(0).getPosition().getY());
        Assertions.assertEquals(17, foods.get(145).getPosition().getX());
        Assertions.assertEquals(19, foods.get(145).getPosition().getY());
    }

    @Test
    void createPowers() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        List<Power> powers = loaderArenaBuilder.createPowers();
        Assertions.assertEquals(4, powers.size());

        Assertions.assertEquals(2, powers.get(0).getPosition().getX());
        Assertions.assertEquals(1, powers.get(0).getPosition().getY());
        Assertions.assertEquals(18, powers.get(3).getPosition().getX());
        Assertions.assertEquals(19, powers.get(3).getPosition().getY());
    }

    @Test
    void createGhosts() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        List<Ghost> ghosts = loaderArenaBuilder.createGhosts();
        Assertions.assertEquals(4, ghosts.size());

        Assertions.assertEquals(10, ghosts.get(0).getPosition().getX());
        Assertions.assertEquals(7, ghosts.get(0).getPosition().getY());
        Assertions.assertEquals(11, ghosts.get(3).getPosition().getX());
        Assertions.assertEquals(9, ghosts.get(3).getPosition().getY());
    }

    @Test
    void createPacman() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));
        Pacman pacman = loaderArenaBuilder.createPacman();

        Assertions.assertEquals(10, pacman.getPosition().getX());
        Assertions.assertEquals(15, pacman.getPosition().getY());

    }

    @Test
    void getLines() throws IOException {
        LoaderArenaBuilder loaderArenaBuilder = Mockito.spy(new LoaderArenaBuilder(1));

        Assertions.assertNotNull(loaderArenaBuilder.getLines());
    }
}

package com.aor.hero.model.arena;

import com.aor.hero.model.elements.Food;
import com.aor.hero.model.elements.Wall;
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
        List<Food> food = loaderArenaBuilder.createFoods();
        Assertions.assertEquals(146, food.size());

        Assertions.assertEquals(3, food.get(0).getPosition().getX());
        Assertions.assertEquals(1, food.get(0).getPosition().getY());
        Assertions.assertEquals(17, food.get(145).getPosition().getX());
        Assertions.assertEquals(19, food.get(145).getPosition().getY());



    }


}

package com.aor.hero.model.elements;

import com.aor.hero.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodTest {

    @Test
    void setPosition(){
        Food food = new Food(10, 15);
        food.setPosition(new Position(10,15));

        Assertions.assertEquals(10, food.getPosition().getX());
        Assertions.assertEquals(15, food.getPosition().getY());
    }
}

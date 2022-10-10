package com.aor.hero.model.elements;

import com.aor.hero.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ElementTest {


    @Test
    void setPosition(){
        Wall wall = new Wall(10, 15);
        wall.setPosition(new Position(10,15));

        Assertions.assertEquals(10, wall.getPosition().getX());
        Assertions.assertEquals(15,wall.getPosition().getY());
    }

}

package com.aor.hero.model.elements;

import com.aor.hero.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PowerTest {

    @Test
    void setPosition() {
        Power power = new Power(10,10);
        power.setPosition(new Position(11,17));

        Assertions.assertEquals(11, power.getPosition().getX());
        Assertions.assertEquals(17, power.getPosition().getY());
    }
}

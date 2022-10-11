package com.aor.hero.model.Menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemTest {

    @Test
    public void toStringTest (){

        //when
        String play = MenuItem.Play.toString();
        String leaderboard = MenuItem.LeaderBoard.toString();
        String exit = MenuItem.Exit.toString();

        //then
        assertEquals(play, "Play");
        assertEquals(leaderboard, "LeaderBoard");
        assertEquals(exit, "Exit");
    }
}

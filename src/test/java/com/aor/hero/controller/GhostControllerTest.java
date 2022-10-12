package com.aor.hero.controller;

import com.aor.hero.controller.Sound.Music;
import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Ghost;
import com.aor.hero.model.elements.Pacman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GhostControllerTest {

    @Test
    void moveGhosts(){
        Pacman pacman = new Pacman(10,10);
        List<Ghost> ghosts = new ArrayList<>();


        //excpetions
        ghosts.add(new Ghost(10,8)); //0
        ghosts.add(new Ghost(10,9)); //1
        ghosts.get(1).setAlive(false);
        ghosts.add(new Ghost(11,8)); //2
        ghosts.add(new Ghost(9,8));  //3

        //dead
        ghosts.add(new Ghost(15,9)); //4
        ghosts.add(new Ghost(5,9));  //5
        ghosts.add(new Ghost(4,9)); //6
        ghosts.add(new Ghost(2,4));  //7
        for(int i = 4; i<=7; i++){ghosts.get(i).setAlive(false);}

        //follow pacman moves
        ghosts.add(new Ghost(10,12)); //8 > will die in the end
        ghosts.add(new Ghost(11,10)); //9 > will remove life

        //PowerON
        ghosts.add(new Ghost(11,17)); //10
        ghosts.get(10).setPowerON(true);



        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.isEmpty(Mockito.any(Position.class))).thenReturn(true);
        Mockito.when(arena.getGhosts()).thenReturn(ghosts);
        Mockito.when(arena.getPacman()).thenReturn(pacman);
        GhostController ghostController = Mockito.spy(new GhostController(arena));

        //When
        ghostController.moveGhosts();

        //Then
        Mockito.verify(ghostController,Mockito.times(3)).teletransport(Mockito.any(Ghost.class));
        Mockito.verify(ghostController,Mockito.times(1)).LoseLifes_KillGhosts(Mockito.any(Ghost.class));

        Assertions.assertEquals(7, ghosts.get(0).getPosition().getY());
        Assertions.assertEquals(8, ghosts.get(1).getPosition().getY());
        Assertions.assertEquals(10, ghosts.get(2).getPosition().getX());
        Assertions.assertEquals(10, ghosts.get(3).getPosition().getX());
        Assertions.assertEquals(14, ghosts.get(4).getPosition().getX());
        Assertions.assertEquals(6, ghosts.get(5).getPosition().getX());
        Assertions.assertEquals(8, ghosts.get(6).getPosition().getY());
        Assertions.assertEquals(5, ghosts.get(7).getPosition().getY());
        Assertions.assertEquals(11, ghosts.get(8).getPosition().getY());
        Assertions.assertEquals(10, ghosts.get(9).getPosition().getX());
        Assertions.assertFalse((ghosts.get(10).getPosition().getX() == 11) && (ghosts.get(10).getPosition().getY() == 17));

        Assertions.assertFalse(pacman.getLifes() == 3);
        Assertions.assertTrue(ghosts.get(1).isAlive());


        //Kill a ghost
        pacman.setPower_status(true);
        ghostController.moveGhosts();

        Assertions.assertFalse(ghosts.get(8).isAlive());
        Assertions.assertEquals(100, arena.getPacman().getScore());


    }
}

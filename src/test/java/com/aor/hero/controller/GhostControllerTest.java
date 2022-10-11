package com.aor.hero.controller;

import com.aor.hero.model.Position;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.model.elements.Ghost;
import com.aor.hero.model.elements.Pacman;
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
        ghosts.add(new Ghost(11,8)); //2
        ghosts.add(new Ghost(9,8));  //3

        //dead
        ghosts.add(new Ghost(15,9)); //4
        ghosts.add(new Ghost(5,9));  //5
        ghosts.add(new Ghost(5,12)); //6
        ghosts.add(new Ghost(2,4));  //7
        for(int i = 4; i<=7; i++){ghosts.get(i).setAlive(false);}

        //random move
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


        //Kill a ghost
        pacman.setPower_status(true);
        ghostController.moveGhosts();
    }
}

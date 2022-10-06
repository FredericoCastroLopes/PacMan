package com.aor.hero.controller;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.viewer.ArenaViewer;

import java.io.IOException;

public class ArenaController extends GameController {

    private final PacmanController pacmanController;
    private final GhostController ghostController;

    private final ArenaViewer viewer;
    private GUI gui;

    public ArenaController(Arena arena, ArenaViewer viewer, GUI gui) {
        super(arena);

        this.viewer = viewer;

        this.gui = gui;

        this.pacmanController = new PacmanController(arena);
        this.ghostController = new GhostController(arena);
    }

    public void start() throws IOException {
        int FPS = 20;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0;

        while (getArena().getPacman().getEnergy() > 0) {
            long startTime = System.currentTimeMillis();

            viewer.draw(getArena());

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            pacmanController.doAction(action);
            if (startTime - lastMonsterMovement > 500) {
                ghostController.moveMonsters();
                lastMonsterMovement = startTime;
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        viewer.close();
    }
}
package com.aor.hero.controller;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.arena.Arena;
import com.aor.hero.viewer.game.ArenaViewer;

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
        int FPS = 12;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0;
        long powerStatus = 0;
        GUI.ACTION last_action = GUI.ACTION.NONE;
        GUI.ACTION saved_action = GUI.ACTION.NONE;

        while (getArena().getPacman().getLifes() > 0) {
            long startTime = System.currentTimeMillis();

            viewer.draw(getArena());

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            if(action != GUI.ACTION.NONE){
                if(pacmanController.doAction(action)) {
                    last_action = action;
                    saved_action = GUI.ACTION.NONE;
                }else{
                    saved_action = action;
                    continue;
                }
            }else {
                if(pacmanController.doAction(saved_action)) {
                    last_action = saved_action;
                    saved_action = GUI.ACTION.NONE;
                }else{
                    pacmanController.doAction(last_action);
                }
            }

            //ChangeSide
            pacmanController.changeSide(last_action);

            if (startTime - lastMonsterMovement > 200) {
                ghostController.moveGhosts();
                lastMonsterMovement = startTime;
            }

            if (arena.getPacman().isPower_status()){
                powerStatus +=1;
            }

            if (powerStatus>=120){
                pacmanController.setPowerOFF();
                ghostController.arena.notifyGhosts(false);
                powerStatus = 0;
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
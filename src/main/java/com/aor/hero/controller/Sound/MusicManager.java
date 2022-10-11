package com.aor.hero.controller.Sound;

public class MusicManager {

    private Music soundTrack;
    private Music kill;
    private Music loselife;
    private Music pacman;
    private Music gameOver;
    private static MusicManager musicManager;

    private MusicManager() {
        soundTrack = new Music("/src/main/resources/Sounds/soundTrack.wav");
        kill = new Music("/src/main/resources/Sounds/siuu.wav");
        loselife = new Music("/src/main/resources/Sounds/lose_life.wav");
        pacman = new Music("/src/main/resources/Sounds/pacman_eating_2.wav");
        gameOver = new Music("/src/main/resources/Sounds/gameOver.wav");
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    public void setSoundTrack(Music soundTrack) {
        this.soundTrack = soundTrack;
    }

    public void setKill(Music kill) {
        this.kill = kill;
    }

    public void setLoselife(Music loselife) {
        this.loselife = loselife;
    }

    public void setPacman(Music pacman) {
        this.pacman = pacman;
    }

    public void setGameOver(Music gameOver) {
        this.gameOver = gameOver;
    }


    public void start(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.startLoop();
            case KILL -> kill.start();
            case LOSELIFE -> loselife.start();
            case PACMAN -> pacman.startLoop();
            case GAMEOVER -> gameOver.startLoop();
        }
    }

    public void stop(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.stop();
            case PACMAN -> pacman.stop();
            case GAMEOVER -> gameOver.stop();
        }
    }

    @SuppressWarnings("UnnecessaryParentheses")
    public boolean isPlaying(Sounds sound) {
        return switch (sound) {
            case SOUNDTRACK -> soundTrack.isPlaying();
            case KILL -> kill.isPlaying();
            case LOSELIFE -> loselife.isPlaying();
            case PACMAN -> pacman.isPlaying();
            case GAMEOVER -> gameOver.isPlaying();
        };
    }

    public void stopAll() {
        soundTrack.stop();
        kill.stop();
        loselife.stop();
        pacman.stop();
        gameOver.stop();
    }
}

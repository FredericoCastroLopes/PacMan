package com.aor.hero.controller.Sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicManagerTest {
    MusicManager musicManagerSpy;
    Music soundTrackMock = Mockito.mock(Music.class);
    Music killMock = Mockito.mock(Music.class);
    Music lose_lifeMock = Mockito.mock(Music.class);
    Music pacmanMock = Mockito.mock(Music.class);
    Music gameOverMock = Mockito.mock(Music.class);

    @BeforeEach
    void initMusicManager() {
        MusicManager musicManager = MusicManager.getInstance();
        musicManager.setSoundTrack(soundTrackMock);
        musicManager.setKill(killMock);
        musicManager.setLoselife(lose_lifeMock);
        musicManager.setPacman(pacmanMock);
        musicManager.setGameOver(gameOverMock);
        musicManagerSpy = Mockito.spy(musicManager);
    }

    @Test
    void startSoundTrack() {
        musicManagerSpy.start(Sounds.SOUNDTRACK);
        Mockito.verify(soundTrackMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startKill() {
        musicManagerSpy.start(Sounds.KILL);
        Mockito.verify(killMock, Mockito.times(1)).start();
    }

    @Test
    void startLoseLife() {
        musicManagerSpy.start(Sounds.LOSELIFE);
        Mockito.verify(lose_lifeMock, Mockito.times(1)).start();
    }

    @Test
    void startPacman() {
        musicManagerSpy.start(Sounds.PACMAN);
        Mockito.verify(pacmanMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startGameOver() {
        musicManagerSpy.start(Sounds.GAMEOVER);
        Mockito.verify(gameOverMock, Mockito.times(1)).startLoop();
    }


    @Test
    void stopSoundTrack() {
        musicManagerSpy.stop(Sounds.SOUNDTRACK);
        Mockito.verify(soundTrackMock, Mockito.times(1)).stop();
    }

    @Test
    void stopPacman() {
        musicManagerSpy.stop(Sounds.PACMAN);
        Mockito.verify(pacmanMock, Mockito.times(1)).stop();
    }

    @Test
    void stopGameOver() {
        musicManagerSpy.stop(Sounds.GAMEOVER);
        Mockito.verify(gameOverMock, Mockito.times(1)).stop();
    }

    @Test
    void isPlayingSoundTrackTrue() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertTrue(playing);
    }

    @Test
    void isPlayingSoundTrackFalse() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertFalse(playing);
    }

    @Test
    void isPlayingKillTrue() {
        Mockito.when(killMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.KILL);
        assertTrue(playing);
    }

    @Test
    void isPlayingKillFalse() {
        Mockito.when(killMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.KILL);
        assertFalse(playing);
    }

    @Test
    void isPlayingLoseLifeTrue() {
        Mockito.when(lose_lifeMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.LOSELIFE);
        assertTrue(playing);
    }

    @Test
    void isPlayingLoseLifeFalse() {
        Mockito.when(lose_lifeMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.LOSELIFE);
        assertFalse(playing);
    }

    @Test
    void isPlayingPacmanTrue() {
        Mockito.when(pacmanMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.PACMAN);
        assertTrue(playing);
    }

    @Test
    void isPlayingPacmanFalse() {
        Mockito.when(pacmanMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.PACMAN);
        assertFalse(playing);
    }

    @Test
    void isPlayingGameOverTrue() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertTrue(playing);
    }

    @Test
    void isPlayingGameOverFalse() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertFalse(playing);
    }


    @Test
    void stopAll() {
        musicManagerSpy.stopAll();
        Mockito.verify(soundTrackMock, Mockito.times(1)).stop();
        Mockito.verify(killMock, Mockito.times(1)).stop();
        Mockito.verify(lose_lifeMock, Mockito.times(1)).stop();
        Mockito.verify(pacmanMock, Mockito.times(1)).stop();
        Mockito.verify(gameOverMock, Mockito.times(1)).stop();
    }
}

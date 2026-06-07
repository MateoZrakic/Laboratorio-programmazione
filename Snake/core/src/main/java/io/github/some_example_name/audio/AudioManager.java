package io.github.some_example_name.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Gestisce tutti gli effetti sonori del gioco Snake.
 * Fornisce metodi statici per riprodurre suoni e liberare risorse audio.
 */
public class AudioManager {

    /** Suono quando il serpente mangia il cibo */
    private static Sound eatSound;

    /** Suono quando il gioco termina */
    private static Sound gameOverSound;

    /**
     * Inizializza i suoni del gioco.
     * Deve essere chiamato nel metodo create() del Main.
     */
    public static void init() {
        eatSound = Gdx.audio.newSound(Gdx.files.internal("eat.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameover.mp3"));
    }

    /**
     * Riproduce il suono di raccolta del cibo.
     */
    public static void playEat() {
        if (eatSound != null) {
            eatSound.play();
        }
    }

    /**
     * Riproduce il suono di game over.
     */
    public static void playGameOver() {
        if (gameOverSound != null) {
            gameOverSound.play();
        }
    }

    /**
     * Libera tutte le risorse audio allocate.
     * Deve essere chiamato alla chiusura del gioco.
     */
    public static void dispose() {
        if (eatSound != null) eatSound.dispose();
        if (gameOverSound != null) gameOverSound.dispose();
    }
}

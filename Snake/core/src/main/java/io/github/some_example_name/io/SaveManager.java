package io.github.some_example_name.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Classe responsabile della gestione del salvataggio e caricamento
 * dell’high score del gioco.
 *
 * Utilizza il file system locale di LibGDX per memorizzare i dati
 * tra diverse esecuzioni del gioco.
 */
public class SaveManager {

    /** Nome del file di salvataggio */
    private static final String FILE = "highscore.txt";

    /**
     * Salva il punteggio massimo nel file locale.
     *
     * @param score punteggio da salvare come nuovo record
     */
    //parte appresa di r/w file
    public static void saveHighScore(int score) {
        try {
            FileHandle file = Gdx.files.local(FILE);
            file.writeString(String.valueOf(score), false);
        } catch (Exception e) {
            System.out.println("Save error: impossibile salvare l'high score");
        }
    }

    /**
     * Carica l’high score salvato dal file locale.
     *
     * @return il punteggio massimo salvato, oppure 0 se il file non esiste
     *         o se si verifica un errore di lettura
     */
    //implemantato da ai per entrare nel file e prendere il punteggio
    public static int loadHighScore() {
        try {
            FileHandle file = Gdx.files.local(FILE);
            return Integer.parseInt(file.readString());
        } catch (Exception e) {
            return 0;
        }
    }
}

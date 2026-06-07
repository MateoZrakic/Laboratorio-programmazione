package io.github.some_example_name.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Rappresenta il cibo del gioco Snake.
 *
 * Il cibo viene posizionato in modo casuale all'interno della griglia
 * e deve essere raccolto dal serpente per aumentare il punteggio e la lunghezza.
 */
public class Food {

    /** Posizione attuale del cibo sulla griglia */
    private Vector2 pos = new Vector2();

    /**
     * Riposiziona il cibo in una posizione casuale della griglia di gioco.
     * La posizione è limitata ai bordi del campo di gioco.
     */
    public void respawn() {
        pos.x = MathUtils.random(0, 24);
        pos.y = MathUtils.random(0, 17);
    }

    /**
     * Restituisce la posizione corrente del cibo.
     *
     * @return vettore contenente coordinate (x, y) del cibo
     */
    public Vector2 getPosition() {
        return pos;
    }
}

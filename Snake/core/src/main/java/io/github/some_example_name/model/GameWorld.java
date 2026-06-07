package io.github.some_example_name.model;

import io.github.some_example_name.audio.AudioManager;

/**
 * Classe principale della logica di gioco.
 * Gestisce lo stato del mondo di gioco, inclusi serpente, cibo,
 * punteggio e controllo delle collisioni.
 */
public class GameWorld {

    /** Istanza del serpente */
    private Snake snake = new Snake();

    /** Istanza del cibo */
    private Food food = new Food();

    /** Punteggio corrente del giocatore */
    private int score = 0;

    /**
     * Costruttore del mondo di gioco.
     * Inizializza la posizione iniziale del cibo.
     */
    public GameWorld() {
        food.respawn();
    }

    /**
     * Aggiorna lo stato del gioco.
     * Gestisce movimento, collisioni, raccolta cibo e punteggio.
     * @return false se il gioco termina (collisione), true altrimenti
     */
    public boolean update() {

        snake.move();


        if (snake.getHead().x == food.getPosition().x &&
            snake.getHead().y == food.getPosition().y) {

            snake.grow();
            food.respawn();
            score += 1;

            AudioManager.playEat();
        }


        if (snake.getHead().x < 0 || snake.getHead().x > 24 ||
            snake.getHead().y < 0 || snake.getHead().y > 17) {
            return false;
        }


        if (snake.checkSelfCollision()) {
            return false;
        }

        return true;
    }

    /**
     * Restituisce il serpente del gioco.
     *
     * @return istanza del serpente
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Restituisce il cibo attuale.
     *
     * @return istanza del cibo
     */
    public Food getFood() {
        return food;
    }

    /**
     * Restituisce il punteggio attuale.
     *
     * @return punteggio del giocatore
     */
    public int getScore() {
        return score;
    }
}

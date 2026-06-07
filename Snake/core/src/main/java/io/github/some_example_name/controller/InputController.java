package io.github.some_example_name.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.model.Direction;
import io.github.some_example_name.model.Snake;

/**
 * Controller responsabile della gestione degli input da tastiera.
 * Traduce i comandi dell’utente in cambi di direzione del serpente.
 *
 * Supporta sia WASD che frecce direzionali.
 */
public class InputController {

    /** Riferimento al serpente controllato */
    private Snake snake;

    /**
     * Costruisce il controller associando il serpente da controllare.
     *
     * @param snake serpente del gioco
     */
    public InputController(Snake snake) {
        this.snake = snake;
    }

    /**
     * Aggiorna lo stato degli input e cambia la direzione del serpente.
     * Viene chiamato ogni frame durante il gameplay.
     */
    public void update() {


        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W))
            snake.setDirection(Direction.UP);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S))
            snake.setDirection(Direction.DOWN);

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A))
            snake.setDirection(Direction.LEFT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D))
            snake.setDirection(Direction.RIGHT);
    }
}

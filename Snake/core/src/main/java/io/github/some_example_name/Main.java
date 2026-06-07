package io.github.some_example_name;

import io.github.some_example_name.model.Direction;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.io.SaveManager;
import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.audio.AudioManager;
import io.github.some_example_name.controller.InputController;
import io.github.some_example_name.model.GameWorld;

/**
 * Classe principale del gioco Snake.
 * Gestisce menu, gameplay, game over, rendering e sistema skin.
 */
public class Main extends ApplicationAdapter {

    /** Batch per rendering grafico */
    private SpriteBatch batch;

    /** Font per UI testuale */
    private BitmapFont font;

    /** Logica del gioco */
    private GameWorld world;

    /** Controller input del serpente */
    private InputController input;

    /** Timer aggiornamento logica */
    private float timer = 0f;

    /** Velocità aggiornamento gioco */
    private float tick = 0.15f;

    /** High score salvato su file */
    private int highScore = 0;

    // ================= SKINS =================

    /** Texture testa serpente */
    private Texture[] snakeHeadSkins;

    /** Texture corpo serpente */
    private Texture[] snakeBodySkins;

    /** Texture cibo */
    private Texture[] foodSkins;

    /** Indice skin serpente selezionata */
    private int snakeSkinIndex = 0;

    /** Indice skin cibo selezionata */
    private int foodSkinIndex = 0;

    /**
     * Stati del gioco (MENU, PLAYING, GAMEOVER)
     */
    private enum State {
        MENU,
        PLAYING,
        GAMEOVER
    }

    /** Stato corrente del gioco */
    private State state = State.MENU;

    /**
     * Inizializza tutte le risorse grafiche e dati iniziali.
     */
    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();

        highScore = SaveManager.loadHighScore();

        AudioManager.init();
        snakeHeadSkins = new Texture[] {
            new Texture("snake_head.png"),
            new Texture("snake_head_blue.png"),
            new Texture("snake_head_yellow.png")
        };

        snakeBodySkins = new Texture[] {
            new Texture("snake_body.png"),
            new Texture("snake_body_blue.png"),
            new Texture("snake_body_yellow.png")
        };

        foodSkins = new Texture[] {
            new Texture("food.png"),
            new Texture("food_orange.png"),
            new Texture("food_purple.png")
        };
    }

    /**
     * Loop principale del gioco.
     * Gestisce gli stati e chiama i rispettivi metodi.
     */
    @Override
    public void render() {

        handleInput();

        switch (state) {
            case MENU:
                renderMenu();
                break;
            case PLAYING:
                renderGame();
                break;
            case GAMEOVER:
                renderGameOver();
                break;
        }
    }

    /**
     * Disegna il menu principale con selezione skin.
     */
    private void renderMenu() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        font.draw(batch, "SNAKE GAME", 355, 500);
        font.draw(batch, "SPACE PER INIZIARE", 330, 420);

        font.draw(batch, "Premi Arrows per cambiare skin", 300, 380);
        font.draw(batch, "Premi W/S per cambiare frutto", 300, 350);



        batch.draw(snakeHeadSkins[snakeSkinIndex], 400, 280, 32, 32);
        batch.draw(snakeBodySkins[snakeSkinIndex], 370, 280, 32, 32);
        batch.draw(snakeBodySkins[snakeSkinIndex], 340, 280, 32, 32);

        batch.draw(foodSkins[foodSkinIndex], 370, 240, 32, 32);

        font.draw(batch, "HIGH SCORE: " + highScore, 330, 200);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            startGame();
        }
    }

    /**
     * Gestisce la logica del gioco durante la partita.
     */
    private void renderGame() {

        if (input != null) {
            input.update();
        }

        timer += Gdx.graphics.getDeltaTime();

        if (timer >= tick) {
            timer = 0;

            boolean alive = world.update();

            if (!alive) {
                AudioManager.playGameOver();
                updateHighScore();
                state = State.GAMEOVER;
            }
        }

        drawGame();
    }

    /**
     * Disegna snake, cibo e UI in game.
     */
    private void drawGame() {

        Gdx.gl.glClearColor(0, 0, 0, 1);//pulizia scermo(ai)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(
            foodSkins[foodSkinIndex],
            world.getFood().getPosition().x * 32,
            world.getFood().getPosition().y * 32,
            32, 32
        );

        Direction dir = world.getSnake().getDirection();

        float rotation;

        switch (dir) {
            case RIGHT:
                rotation = 0f;
                break;
            case UP:
                rotation = 90f;
                break;
            case LEFT:
                rotation = 180f;
                break;
            case DOWN:
                rotation = -90f;
                break;
            default:
                rotation = 0f;
                break;
        }

        for (int i = 0; i < world.getSnake().getBody().size; i++) {

            Vector2 p = world.getSnake().getBody().get(i);

            if (i == 0) {

                batch.draw(
                    snakeHeadSkins[snakeSkinIndex],
                    p.x * 32,
                    p.y * 32,
                    16, 16,
                    32, 32,
                    1, 1,
                    rotation,
                    0, 0,
                    snakeHeadSkins[snakeSkinIndex].getWidth(),
                    snakeHeadSkins[snakeSkinIndex].getHeight(),
                    false,
                    false
                );

            } else {

                batch.draw(
                    snakeBodySkins[snakeSkinIndex],
                    p.x * 32,
                    p.y * 32,
                    32, 32
                );
            }
        }

        font.draw(batch, "SCORE: " + world.getScore(), 20, 470);

        batch.end();
    }

    /**
     * Schermata game over.
     */
    private void renderGameOver() {

        Gdx.gl.glClearColor(0.2f, 0, 0, 1);//ai
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        font.draw(batch, "GAME OVER", 350, 400);
        font.draw(batch, "SPACE PER MENU", 320, 360);
        font.draw(batch, "SCORE: " + world.getScore(), 330, 320);
        font.draw(batch, "HIGH SCORE: " + highScore, 300, 280);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            state = State.MENU;
        }
    }

    /**
     * Gestisce input del menu (cambio skin).
     */
    private void handleInput() {

        if (state != State.MENU) return;

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            snakeSkinIndex = (snakeSkinIndex + 1) % snakeHeadSkins.length;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            snakeSkinIndex = (snakeSkinIndex - 1 + snakeHeadSkins.length) % snakeHeadSkins.length;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            foodSkinIndex = (foodSkinIndex + 1) % foodSkins.length;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            foodSkinIndex = (foodSkinIndex - 1 + foodSkins.length) % foodSkins.length;
        }
    }

    /**
     * Avvia una nuova partita.
     */
    private void startGame() {
        world = new GameWorld();
        input = new InputController(world.getSnake());
        timer = 0;
        state = State.PLAYING;
    }

    /**
     * Aggiorna e salva high score se necessario.
     */
    private void updateHighScore() {

        int score = world.getScore();

        if (score > highScore) {
            highScore = score;
            SaveManager.saveHighScore(highScore);
        }
    }

    /**
     * Libera tutte le risorse del gioco.
     */
    @Override
    public void dispose() {

        batch.dispose();
        font.dispose();

        for (Texture t : snakeHeadSkins) t.dispose();
        for (Texture t : snakeBodySkins) t.dispose();
        for (Texture t : foodSkins) t.dispose();

        AudioManager.dispose();
    }
}

package io.github.some_example_name.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.model.Direction;

/**
 * Rappresenta il serpente del gioco Snake.
 *
 * Gestisce il corpo del serpente, il movimento, la crescita
 * e il controllo delle collisioni con sé stesso.
 */
public class Snake {

    /** Corpo del serpente rappresentato come lista di segmenti */
    //parte vector appresa dall ai, per non avere la variabile x e y , ma tutto in uno
    private Array<Vector2> body = new Array<>();

    /** Direzione corrente del movimento */
    private Direction direction = Direction.RIGHT;

    /** Indica se il serpente deve crescere nel prossimo movimento */
    private boolean grow = false;

    /**
     * Costruttore del serpente.
     * Inizializza il corpo con una singola cella iniziale.
     */
    public Snake() {
        body.add(new Vector2(5, 5));
        body.add(new Vector2(4, 5));
        body.add(new Vector2(3, 5));
    }

    /**
     * Imposta la direzione del serpente.
     * Impedisce l'inversione immediata (es: destra → sinistra).
     *
     * @param d nuova direzione desiderata
     */
    public void setDirection(Direction d) {

        // evita inversione istantanea (regola classica Snake)
        if (this.direction == Direction.UP && d == Direction.DOWN) return;
        if (this.direction == Direction.DOWN && d == Direction.UP) return;
        if (this.direction == Direction.LEFT && d == Direction.RIGHT) return;
        if (this.direction == Direction.RIGHT && d == Direction.LEFT) return;

        this.direction = d;
    }

    /**
     * Restituisce la testa del serpente.
     *
     * @return posizione della testa
     */
    public Vector2 getHead() {
        return body.first();
    }

    /**
     * Muove il serpente nella direzione corrente.
     * Gestisce anche la crescita quando necessario.
     */
    public void move() {

        Vector2 head = getHead().cpy();

        switch (direction) {
            case UP:
                head.y++;
                break;

            case DOWN:
                head.y--;
                break;

            case LEFT:
                head.x--;
                break;

            case RIGHT:
                head.x++;
                break;
        }

        body.insert(0, head);

        if (!grow) {
            body.pop();
        } else {
            grow = false;
        }
    }

    /**
     * Attiva la crescita del serpente.
     * La crescita avviene nel prossimo movimento.
     */
    public void grow() {
        grow = true;
    }

    /**
     * Controlla se il serpente ha colpito sé stesso.
     *
     * @return true se c'è collisione, false altrimenti
     */
    public boolean checkSelfCollision() {

        Vector2 head = getHead();

        for (int i = 1; i < body.size; i++) {
            if (body.get(i).equals(head)) return true;
        }

        return false;
    }

    /**
     * Restituisce il corpo completo del serpente.
     *
     * @return lista dei segmenti del corpo
     */
    public Array<Vector2> getBody() {
        return body;
    }
    public Direction getDirection() {
        return direction;
    }
}

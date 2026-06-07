package io.github.some_example_name.exeptions;

/**
 * Eccezione personalizzata lanciata quando il gioco termina.
 * Viene utilizzata per interrompere il flusso di esecuzione
 * in caso di game over (collisione con i bordi o con il corpo del serpente).
 */
public class GameOverException extends RuntimeException {

    /**
     * Crea una nuova eccezione di Game Over.
     *
     * @param message messaggio descrittivo dell’errore o della causa del game over
     */
    public GameOverException(String message) {
        super(message);
    }
}

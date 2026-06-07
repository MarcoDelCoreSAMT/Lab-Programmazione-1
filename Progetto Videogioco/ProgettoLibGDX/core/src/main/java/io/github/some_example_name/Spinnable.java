package io.github.some_example_name;

/**
 * Interfaccia per oggetti che ruotano su loro stessi.
 */

public interface Spinnable {

    /**
     * Aggiorna l'angolo di rotazione in base al tempo trascorso.
     * @param delta tempo trascorso dall'ultimo frame in secondi
     */
    void updateSpin(float delta);

    /**
     * Restituisce l'angolo di rotazione corrente in gradi.
     * @return angolo in gradi (0-360)
     */
    float getAngle();

    /**
     * Restituisce la velocità di rotazione in gradi al secondo.
     * @return velocità di rotazione
     */
    float getSpinSpeed();
}

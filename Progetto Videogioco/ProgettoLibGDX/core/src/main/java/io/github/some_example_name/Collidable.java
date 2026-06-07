package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

/**
 * Interfaccia generica per gli oggetti con il quale il giocatore può collidere
 */

public interface Collidable {
    float getX();
    float getY();
    // Restituisce bordi di collisione
    Rectangle getBounds();
}

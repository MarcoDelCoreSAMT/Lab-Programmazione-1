package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

/**
 * I teschi Sterling Ruby fungono da monetine collezionabili
 */

public class SterlingRuby implements Collect {
    private float x, y, baseY;
    private boolean collected;
    private Rectangle bounds;

    // Riprendo grandezza da vecchio codice
    private static final float WIDTH = 45f;
    private static final float HEIGHT = 55f;

    public SterlingRuby(float x, float y) {
        this.x = x;
        this.y = y;
        this.baseY = y;
        this.collected = false;
        this.bounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void collect() {
        collected = true;
        // Lo mando in vacanza da qualche parte (sotto terra)
        bounds.setPosition(-9999f, -9999f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    // Aggiorna y con oscillazione (fluttuazione)
    public void update(float skullT) {
        if (!collected) {
            y = baseY + (float)(Math.sin(skullT * 4.5f) * 4f);
            bounds.setPosition(x, y);
        }
    }
}

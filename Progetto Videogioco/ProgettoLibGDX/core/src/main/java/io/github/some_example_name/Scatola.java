package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Casse di legno che fungono da ostacolo nel livello
 */

public class Scatola implements Collidable {
    private float x, y, w, h;
    private Rectangle bounds;
    private Texture textureScatole;

    public Scatola(Texture textureScatole, float x, float y, float w, float h) {
        this.textureScatole = textureScatole;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bounds = new Rectangle(x,y,w,h);
    }

    @Override public Rectangle getBounds() {return bounds;}
    @Override public float getX() {return x;}
    @Override public float getY() {return y;}

    public float getW() {return w;}
    public float getH() {return h;}

    public void draw(SpriteBatch batch, float cameraX) {
        batch.draw(textureScatole, x - cameraX + 80f, y + 15f, w, h);
    }
}

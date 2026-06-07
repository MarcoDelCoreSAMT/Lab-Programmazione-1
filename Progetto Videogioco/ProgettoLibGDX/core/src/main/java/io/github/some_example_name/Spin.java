package io.github.some_example_name;

public class Spin implements Spinnable{
    private float angle = 0f;
    private static final float SPIN_SPEED = 80f; // gradi al secondo

    @Override
    public void updateSpin(float delta) {
        angle -= SPIN_SPEED * delta;
        if (angle > 360f) angle -= 360f;
    }

    @Override
    public float getAngle() { return angle; }

    @Override
    public float getSpinSpeed() { return SPIN_SPEED; }

    public void update(float delta) {
        updateSpin(delta);
    }
}

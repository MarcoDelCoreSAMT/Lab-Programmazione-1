package io.github.some_example_name;

import com.badlogic.gdx.Game;

/*
Punto d'ingresso principale del gioco.
Gestisce il passaggio tra le schermate.
Consigliato da claude AI.
 */

public class SkeletrixIsland extends Game {
    @Override
    public void create() {
        // Lancia la schermata iniziale (Hubert)
        setScreen(new Intro(this));
    }
}

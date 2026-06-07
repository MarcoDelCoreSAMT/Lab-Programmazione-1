package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Impostazione del livello (teschi e scatole):
 * Per cambiare la quantità e la posizione degli oggetti.
 */

public class GestoreLivelli {

    /**
     * Crea e restituisce tutti gli elementi del livello
     * @param skullSR texture teschio sterling ruby
     * @param cr1 texture cassa tipo 1
     * @param cr2 texture cassa tipo 2
     */

    public static Array<SterlingRuby> createSkulls() {
        Array<SterlingRuby> skulls = new Array<>();

        // Punto di modifica con --> new SterlingRuby(x, y)
        skulls.add(new SterlingRuby(300, 200));

        skulls.add(new SterlingRuby(600, 300));
        skulls.add(new SterlingRuby(820, 200));
        skulls.add(new SterlingRuby(1400, 180));
        skulls.add(new SterlingRuby(1800, 180));

        return skulls;
    }

    public static Array<Scatola> createCrates(Texture cr1, Texture cr2){
        Array<Scatola> scatole = new Array<>();

        scatole.add(new Scatola(cr1, 360,55, 65, 65));
        scatole.add(new Scatola(cr1, 425,55, 65, 65));
        scatole.add(new Scatola(cr1, 490,55, 65, 65));
        scatole.add(new Scatola(cr1, 555,55, 65, 65));
        scatole.add(new Scatola(cr1, 620,55, 65, 65));

        scatole.add(new Scatola(cr1, 490,120, 65, 65));
        scatole.add(new Scatola(cr1, 555,120, 65, 65));
        scatole.add(new Scatola(cr1, 620,120, 65, 65));

        scatole.add(new Scatola(cr2, 1115,70, 65, 65));

        return scatole;
    }

    // Tot teschi nel livello
    public static int total() {
        return createSkulls().size;
    }
}

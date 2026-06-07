package io.github.some_example_name;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Unit Test sulla classe "GestoreLivelli"
1) Va a controllare se il totale equivale alla quantità della lista
2) Controlla se conta veramente (se fosse uguale a zero non sarebbe così)
Non so come avviarli...
 */

class GestoreLivelliTest {
    @Test
    void shouldReturnNumberOfSkulls() {
        Array<SterlingRuby> skulls = GestoreLivelli.createSkulls();
        Assertions.assertEquals(GestoreLivelli.total(), skulls.size);
    }

    @Test
    void shouldReturnTotaleCoerente(){
        Assertions.assertTrue(GestoreLivelli.total() > 0);
    }
}

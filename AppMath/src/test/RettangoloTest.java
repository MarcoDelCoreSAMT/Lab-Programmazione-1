package test;

import forme.Rettangolo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloTest {

    @ParameterizedTest
    @CsvSource({
            "3.0, 4.0, 12.0",
            "5.0, 5.0, 25.0",
            "1.0, 10.0, 10.0",
            "2.5, 4.0, 10.0"
    })
    public void testArea(double base, double altezza, double areaAttesa){
        // ARRANGE
        Rettangolo r = new Rettangolo(base, altezza);

        // ACT
        double risultato = r.area();

        // ASSERT (delta indica differenza di risultato accettata)
        assertEquals(areaAttesa, risultato, 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "3.0, 4.0, 14.0",
            "5.0, 5.0, 20.0",
            "1.0, 10.0, 22.0",
            "2.5, 4.0, 13.0"
    })
    public void testPerimetro(double base, double altezza, double perimetroAtteso){
        // ARRANGE
        Rettangolo r = new Rettangolo(base, altezza);

        //ACT
        double risultato = r.perimetro();

        // ASSERT
        assertEquals(perimetroAtteso, risultato, 0.001);
    }

}
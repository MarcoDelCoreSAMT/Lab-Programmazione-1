package test;

import forme.Cerchio;
import forme.Rettangolo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CerchioTest {
    @ParameterizedTest
    @CsvSource({
            "3.0, 28.274",
            "2.0, 12.566",
            "2.5231, 20.0"
    })
    public void testAreaCerchio(double raggio, double areaAttesa) {
        // ARRANGE
        Cerchio c = new Cerchio(raggio);

        //ACT
        double risultato = c.area();

        // ASSERT
        assertEquals(areaAttesa, risultato, 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 31.416",
            "4.7, 29.53",
            "6.3, 39.584"
    })
    public void testAreaRettangolo(double raggio, double perimetroAtteso) {
        // ARRANGE
        Cerchio c = new Cerchio(raggio);

        //ACT
        double risultato = c.perimetro();

        // ASSERT
        assertEquals(perimetroAtteso, risultato, 0.001);
    }
}
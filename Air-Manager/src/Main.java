// Esercizio: 10. Air-Manager
// Autore: Marco Del Core

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        VoloInternazionale volo = new VoloInternazionale(
                "LH456",
                LocalDate.of(2026, 1, 26),
                364.99,
                "Tokyo",
                "Mario Rossi",
                true
        );

        System.out.println(volo.generaTicket());
    }
}
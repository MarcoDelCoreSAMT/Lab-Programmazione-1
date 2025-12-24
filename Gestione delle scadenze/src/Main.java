// Esercizio: 8.1 Gestione delle scadenze
// Autore: Marco Del Core

import java.util.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Base> elementi = new ArrayList<>();
        Date oggi = new Date();

        Prestito prestito = new Prestito("A001", oggi, 7);
        Abbonamento abbonamento = new Abbonamento("B001", oggi, 1);

        // stesso codice → equals() true
        Prestito p2 = new Prestito("X999", oggi, 10);
        Abbonamento a2 = new Abbonamento("X999", oggi, 12);

        elementi.add(prestito);
        elementi.add(abbonamento);

        System.out.println("=== TUTTI GLI ELEMENTI ===");
        for (Base e : elementi) {
            System.out.println(e);
            System.out.println();
        }

        System.out.println("=== ELEMENTI SCADUTI ===");
        for (Base e : elementi) {
            if (e.isScaduto(new Date())) {
                System.out.println(e);
            }
        }

        System.out.println("\n=== TEST EQUALS ===");
        System.out.println("p2.equals(a2)? " + p2.equals(a2));
    }
}
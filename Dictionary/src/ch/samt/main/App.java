// Esercizio: Dictionary
// Autore: Marco Del Core

package ch.samt.main;

import ch.samt.dictionary.Dictionary;
import ch.samt.dictionary.Entry;

public class App {
    public static void main(String[] args) {
        Dictionary d = new Dictionary();

        d.aggiungi(new Entry("Dottore", "Doctor"));
        d.aggiungi(new Entry("Cane", "Dog"));
        d.aggiungi(new Entry("Eliott", "PippoPallino"));

        Entry trovata = d.cerca("gatto");
        if (trovata != null) {
            System.out.println("Traduzione trovata: " + trovata);
        } else {
            System.out.println("Parola non trovata.");
        }

        Entry trovata2 = d.cerca("Cane");
        if (trovata2 != null) {
            System.out.println("Traduzione trovata: " + trovata2);
        } else {
            System.out.println("Parola non trovata.");
        }

        System.out.println();

        d.stampaTutto();
    }
}
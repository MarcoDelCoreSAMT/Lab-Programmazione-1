// Esercizio: 6.6 Gestione Biblioteca Scolastica
// Autore: Marco Del Core

package ch.samt.biblioteca.app;

import ch.samt.biblioteca.model.*;
import ch.samt.biblioteca.data.Biblioteca;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();

        Libro l1 = new Libro("L001", "Dorian Law", 2008, "A1", "Elsileo inSilver", 123);
        Libro l2 = new Libro("L002", "La vita di Eliott", 2011, "A2", "Ramon Pergoletti", 13);
        Dvd d1 = new Dvd("D001", "Sotirios Tuff", 2024, "M1", "Fabio Maruca", 104);

        // Aggiunta al catalogo
        System.out.println("Aggiungo L001: " + b.aggiungiItem(l1));
        System.out.println("Aggiungo L002: " + b.aggiungiItem(l2));
        System.out.println("Aggiungo D001: " + b.aggiungiItem(d1));

        // Aggiunta di un duplicato
        Libro duplicato = new Libro("L001", "Nome duplicato", 2020, "B1", "Autore X", 123);
        System.out.println("\nProvo ad aggiungere duplicato L001: " + b.aggiungiItem(duplicato));

        System.out.println("\n--- Catalogo completo ---");
        ArrayList<ItemBiblioteca> catalogo = b.getCatalogo();
        for (ItemBiblioteca it : catalogo) {
            System.out.println(it);
        }

        // Stampa libro x autore
        System.out.println("\n--- Libri di 'Elsileo inSilver' ---");
        ArrayList<ItemBiblioteca> eco = b.getElementiDiAutore("Elsileo inSilver");
        for (ItemBiblioteca it : eco) {
            System.out.println(it);
        }

        // -- Test FIFO prenotazioni --
        System.out.println("\n--- Test FIFO (prenotazioni) ---");
        b.aggiungiPrenotazioneFIFO(l1);
        b.aggiungiPrenotazioneFIFO(d1);
        b.aggiungiPrenotazioneFIFO(l2);

        ItemBiblioteca prossimaPren = b.prossimaPrenotazioneFIFO();
        System.out.println("Estratto (FIFO) -> " + (prossimaPren != null ? prossimaPren : "null"));


        // -- Test LIFO (consegne urgenti) --
        System.out.println("\n--- Test LIFO (consegne urgenti) ---");
        b.aggiungiConsegnaUrgenteLIFO(l1);
        b.aggiungiConsegnaUrgenteLIFO(d1);
        b.aggiungiConsegnaUrgenteLIFO(l2);

        ItemBiblioteca prossimaConsegna = b.prossimaConsegnaLIFO();
        System.out.println("Estratto (LIFO) -> " + (prossimaConsegna != null ? prossimaConsegna : "null"));
    }
}
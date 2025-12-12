package ch.samt.biblioteca.data;

import java.util.*;

import ch.samt.biblioteca.model.*;

public class Biblioteca {
    private ArrayList<ItemBiblioteca> catalogo;
    private Set<String> codiciUsati;
    private Map<String, ArrayList<ItemBiblioteca>> elementiPerAutore;
    private Queue<ItemBiblioteca> prenotazioniFIFO;
    private Stack<ItemBiblioteca> consegneUrgentiLIFO;

    public Biblioteca(){
        this.catalogo = new ArrayList<>();
        this.codiciUsati = new HashSet<>();
        this.elementiPerAutore = new HashMap<>();
        this.prenotazioniFIFO = new LinkedList<>();
        this.consegneUrgentiLIFO = new Stack<>();
    }

    public boolean aggiungiItem(ItemBiblioteca item){
        if (item == null){
            return false;
        }
        String codice = item.getCodice();

        if (codice == null){
            return false;
        }

        // check duplicati
        if (codiciUsati.contains(codice)) {
            return false;
        }

        // aggiunta libro al catalogo
        catalogo.add(item);
        codiciUsati.add(codice);

        // check se è un libro, aggiorno map autore
        if (item instanceof Libro) {
            Libro libro = (Libro) item;
            String autore = libro.getAutore();
            if (autore != null) {
                elementiPerAutore.putIfAbsent(autore, new ArrayList<>());
                elementiPerAutore.get(autore).add(libro);
            }
        }

        return true;
    }

    public ArrayList<ItemBiblioteca> getCatalogo(){
        return new ArrayList<>(catalogo);
    }

    // Ritorna lista item per autore (solo libri), se vuota --> lista vuota
    public ArrayList<ItemBiblioteca> getElementiDiAutore(String autore){
        if (autore == null) return new ArrayList<>();
        ArrayList<ItemBiblioteca> lista = elementiPerAutore.get(autore);
        if (lista == null) return new ArrayList<>();
        return new ArrayList<>(lista);
    }

    // -- Parte prenotazioni FIFO --

    public void aggiungiPrenotazioneFIFO(ItemBiblioteca item){
        if (item != null) {
            prenotazioniFIFO.add(item);
        }
    }

    // Ritorna la prossima prenotazione (first in) oppure null se vuota
    public ItemBiblioteca prossimaPrenotazioneFIFO(){
        return prenotazioniFIFO.poll();     // poll() ritorna null se vuota
    }

    // -- Parte consegne urgenti LIFO --

    public void aggiungiConsegnaUrgenteLIFO(ItemBiblioteca item){
        if (item != null) {
            consegneUrgentiLIFO.push(item);
        }
    }

    // Ritorna la prossima consegna urgente (last in) oppure null se vuota
    public ItemBiblioteca prossimaConsegnaLIFO(){
        if (consegneUrgentiLIFO.isEmpty()) return null;
        return consegneUrgentiLIFO.pop();
    }
}

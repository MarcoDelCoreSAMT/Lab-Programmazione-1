package ch.samt.biblioteca.model;

public class Libro extends ItemBiblioteca{
    private String autore;
    private int numeroPagine;

    public Libro(String codice, String titolo, int annoPubblicazione, String scaffale, String autore, int numeroPagine) {
        super(codice, titolo, annoPubblicazione, scaffale);
        this.autore = autore;
        this.numeroPagine = numeroPagine;
    }

    public String getAutore() {
        return autore;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString(){
        return String.format("Libro[codice=%s, titolo=%s, autore=%s, anno=%d, pagine=%d, scaffale=%s]",
                getCodice(), getTitolo(), autore, getAnnoPubblicazione(), numeroPagine, getScaffale());
    }
}

package ch.samt.biblioteca.model;

public class Dvd extends ItemBiblioteca{
    private String regista;
    private double durataMinuti;

    public Dvd(String codice, String titolo, int annoPubblicazione, String scaffale, String regista, double durataMinuti) {
        super(codice, titolo, annoPubblicazione, scaffale);
        this.regista = regista;
        this.durataMinuti = durataMinuti;
    }

    public String getRegista() {
        return regista;
    }

    public double getDurataMinuti() {
        return durataMinuti;
    }

    @Override
    public String toString(){
        return String.format("Dvd[codice=%s, titolo=%s, regista=%s, anno=%d, durata=%f min, scaffale=%s]",
                getCodice(), getTitolo(), regista, getAnnoPubblicazione(), durataMinuti, getScaffale());
    }
}

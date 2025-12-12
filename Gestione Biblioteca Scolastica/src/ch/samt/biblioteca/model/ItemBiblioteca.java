package ch.samt.biblioteca.model;

import java.util.Objects;

public class ItemBiblioteca {
    private String codice;
    private String titolo;
    private int annoPubblicazione;
    protected String scaffale;

    public ItemBiblioteca(String codice, String titolo, int annoPubblicazione, String scaffale){
        this.codice = codice;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.scaffale = scaffale;
    }

    public String getCodice() {
        return codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getScaffale() {
        return scaffale;
    }

    public void setScaffale(String scaffale) {
        this.scaffale = scaffale;
    }

    @Override
    public String toString(){
        return String.format("Item[codice=%s, titolo=%s, anno=%d, scaffale=%s]",
                codice, titolo, annoPubblicazione, scaffale);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemBiblioteca other = (ItemBiblioteca) obj;
        return Objects.equals(codice, other.codice);
    }

}

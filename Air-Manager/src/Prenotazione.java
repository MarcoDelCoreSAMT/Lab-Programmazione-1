import java.time.LocalDate;
import java.util.regex.Pattern;

public abstract class Prenotazione implements Documentabile {
    private String codiceVolo;
    private LocalDate dataPartenza;
    private double prezzoBase;

    public Prenotazione(String codiceVolo, LocalDate dataPartenza, double prezzoBase) {
        this.codiceVolo = codiceVolo;
        this.dataPartenza = dataPartenza;
        this.prezzoBase = prezzoBase;
    }

    public String getCodiceVolo() {
        return codiceVolo;
    }

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }

    public void setCodiceVolo(String codiceVolo) {
        if (!Pattern.matches("^[A-Z]{2}\\\\d{3}$", codiceVolo)) {
            throw new IllegalArgumentException("Codice volo non valido");
        }
        this.codiceVolo = codiceVolo;
    }

    public void setDataPartenza(LocalDate dataPartenza) {
        if (dataPartenza.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data di partenza non valida");
        }
        this.dataPartenza = dataPartenza;
    }

    public void setPrezzoBase(double prezzoBase) {
        if (prezzoBase <= 0) {
            throw new IllegalArgumentException("Prezzo base non valido");
        }
        this.prezzoBase = prezzoBase;
    }

    public abstract double calcolaCostoTotale();
}

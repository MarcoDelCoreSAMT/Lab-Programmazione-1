import java.util.Date;
import java.util.Objects;

public abstract class Base {
    protected String id;
    protected Date dataCreazione;

    public Base(String id, Date dataCreazione) {
        this.id = id;
        this.dataCreazione = dataCreazione;
    }

    public String getId() {
        return id;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public abstract Date calcolaScadenza();

    public boolean isScaduto(Date dataRiferimento) {
        return calcolaScadenza().before(dataRiferimento);
    }

    public String toString(){
        return "Data di creazione: " + dataCreazione +
                "\nData di scadenza: " + calcolaScadenza();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(id, base.id) && Objects.equals(dataCreazione, base.dataCreazione);
    }
}

import java.util.Date;
import java.util.Calendar;

public class Prestito extends Base {
    private int durataGiorni;

    public Prestito(String id, Date dataCreazione, int durataGiorni) {
        super(id, dataCreazione);
        this.durataGiorni = durataGiorni;
    }

    public int getDurataGiorni() {
        return durataGiorni;
    }

    @Override
    public Date calcolaScadenza() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataCreazione);
        cal.add(Calendar.DAY_OF_MONTH, durataGiorni);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "[PRESTITO LIBRO]\n" + super.toString() +
                "\nDurata: " + durataGiorni + " giorni";
    }
}

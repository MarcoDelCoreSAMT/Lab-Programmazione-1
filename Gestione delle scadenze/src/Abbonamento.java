import java.util.Date;
import java.util.Calendar;

public class Abbonamento extends Base {
    private int durataMesi;

    public Abbonamento(String id, Date dataCreazione, int durataMesi) {
        super(id, dataCreazione);
        this.durataMesi = durataMesi;
    }

    @Override
    public Date calcolaScadenza() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataCreazione);
        cal.add(Calendar.MONTH, durataMesi);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "[ABBONAMENTO]\n" + super.toString() +
                "\nDurata: " + durataMesi + " mesi";
    }
}

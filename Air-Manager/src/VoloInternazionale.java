import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class VoloInternazionale extends Prenotazione{
    private String destinazione;
    private String nomePasseggero;
    private boolean bagaglioStiva;

    private static final Set<String> EXTRA_EUROPA = Set.of(
            "TOKYO", "NEW YORK", "DUBAI"
    );

    public VoloInternazionale(String codiceVolo, LocalDate dataPartenza, double prezzoBase, String destinazione, String nomePasseggero, boolean bagaglioStiva) {
        super(codiceVolo, dataPartenza, prezzoBase);
        this.destinazione = destinazione;
        this.nomePasseggero = nomePasseggero;
        this.bagaglioStiva = bagaglioStiva;
    }

    @Override
    public double calcolaCostoTotale() {
        double totale = getPrezzoBase() + 25;
        if (bagaglioStiva){
            totale += 40;
        }
        return totale;
    }

    @Override
    public String generaTicket(){
        StringBuilder ticket = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.ITALIAN);

        // Elenco di esempio per controllo passaporto (semplificato)
        List<String> extraEU = Arrays.asList("TOKYO", "NEW YORK", "DUBAI", "LONDRA");

        ticket.append("**************************************************\n");
        ticket.append("* BOARDING PASS                                  *\n");
        ticket.append("***************************************************\n");
        ticket.append("PASSEGGERO:     ").append((nomePasseggero).toUpperCase()).append("\n");
        ticket.append("VOLO:           ").append(getCodiceVolo()).append("\n");
        ticket.append("DESTINAZIONE:   ").append((destinazione).toUpperCase()).append("\n");
        ticket.append(String.format("DATA:           %s\n", getDataPartenza().format(formatter)));

        ticket.append("--------------------------------------------------\n");

        if (EXTRA_EUROPA.contains(destinazione.toUpperCase())) {
            ticket.append("[INFO: Necessario Passaporto]\n");
            ticket.append("--------------------------------------------------\n");
        }

        ticket.append("BAGAGLIO IN STIVA: ")
                .append(bagaglioStiva ? "SI" : "NO").append("\n");

        ticket.append(String.format(
                "PREZZO FINALE:     € %.2f\n", calcolaCostoTotale()
        ));

        ticket.append("***************************************************\n");

        return ticket.toString();
    }
}

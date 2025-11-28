// Es. House with Room
// Autore: Marco Del Core
public class Main {
    public static void main(String[] args) {

        House casa = new House();

        casa.aggiungiStanza("Cucina", 15.5);
        casa.aggiungiStanza("Camera di Dorian", 6.7);
        casa.aggiungiStanza("Bagno lercio", 320.88);

        casa.visualizzaStanze();

        casa.getSuperificieTotale();

        System.out.println("\nCerco... :\n" + casa.trovaStanza("Cucina"));

        System.out.println("\nRimuovo quel bagno merdoso... \n");
        casa.rimuoviStanza("Bagno lercio");

        casa.visualizzaStanze();
    }
}
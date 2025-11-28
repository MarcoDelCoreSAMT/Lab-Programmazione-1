import java.util.ArrayList;

public class House {
    private ArrayList<Room> stanze;

    public House(){
        stanze = new ArrayList<>();
    }

    public void aggiungiStanza(String nome, double superficieMq){
        stanze.add(new Room(nome,superficieMq));
    }

    public void visualizzaStanze(){
        if (stanze.isEmpty()){
            System.out.println("La casa è vuota");
            return;
        }

        System.out.println("Stanze presenti nella casa: ");
        for (Room r : stanze){
            System.out.println("- "+ r);
        }
    }

    public double getSuperificieTotale(){
        double tot = 0;
        for (Room r : stanze) {
                tot += r.getSuperficieMq();
        }
        return tot;
    }

    public Room trovaStanza(String nome){
        for (Room r : stanze){
            if (r.getNome().equalsIgnoreCase(nome)){
                return r;
            }
        }
        return null;
    }

    public boolean rimuoviStanza(String nome){
        Room r = trovaStanza(nome);
        if (r != null){
            stanze.remove(r);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main (String[] args){

        try {
            Matrix m = new Matrix(4,6);
            m.popolaMatrice();;
            System.out.println("Matrice generata:");
            m.stampaMatrice();

            System.out.println("\nValore in (2,3): " + m.getCella(2,3));

            System.out.println("\nModifica cella (1,1) a 1");
            m.setCella(1, 1, 1);

            m.stampaMatrice();

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
    }
}

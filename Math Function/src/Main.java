// Esercizio: 13.0 Math Function
// Autore: Marco Del Core
public class Main {
    public static void main(String[] args) {
        int abs1 = FunctionMath.abs(-12);
        System.out.println("Valore assoluto di un int: " + abs1);

        double abs2 = FunctionMath.abs(-14.34);
        System.out.println("\nValore assoluto di un double: " + abs2);

        boolean prime = FunctionMath.isPrime(67);
        System.out.println("\nÈ un numero primo?: " + prime);

        double hyp = FunctionMath.hypotenuse(3,4);
        System.out.println("\nIpotenusa dei due lati: " + hyp);
    }
}
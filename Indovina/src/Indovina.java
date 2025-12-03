// 5.1 Indovina
// Autore: Marco Del Core
import java.util.Scanner;
import java.util.Random;

public class Indovina {
    public static void main(String[] args) {

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        int num = 0;
        int numcas = rnd.nextInt(100) + 1;;
        int count = 0;

        while (num != numcas){
            System.out.print("\nIndovina il numero tra 1 a 100: ");
            num = sc.nextInt();
            count++;
            System.out.print("Numero scelto: "+ num);
            if (num > numcas){
                System.out.print("\nIl numero scelto è più grande del numero da indovinare...");
                continue;
            }
            if (num < numcas){
                System.out.print("\nIl numero scelto è più piccolo del numero da indovinare...");
                continue;
            }
            else {
                System.out.print("\nBravo! Hai indovinato in " + count + " tentativi.");
            }
        }
        sc.close();
    }
}
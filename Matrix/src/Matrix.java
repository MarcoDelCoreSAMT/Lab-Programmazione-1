// Matrix
// Autore: Marco Del Core

import java.util.Random;

public class Matrix {
    private int righe;
    private int colonne;
    private int[][] valori;

    // C vuoto
    public Matrix() {
        this.righe = 5;
        this.colonne = 5;
        valori = new int [righe][colonne];
    }

    // Costrutture righe e colonne scelte
    public Matrix(int righe, int colonne){
        if (righe <= 0 || colonne <= 0){
            throw new IllegalArgumentException("Righe e colonne devono essere > 0.");
        }
        this.righe = righe;
        this.colonne = colonne;
        valori = new int [righe][colonne];
    }

    // Getter
    public int getRighe(){
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    // Stampa l'intera matrice su console
    public void stampaMatrice() {
        for (int i = 0; i < righe; i++){
            for (int j = 0; j < colonne; j++){
                System.out.print(valori[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Popola la matrice con valori casuali da 0 a 1
    public void popolaMatrice() {
        Random rand = new Random();
        for (int i = 0; i < righe; i++){
            for (int j = 0; j < colonne; j++){
                valori[i][j] = rand.nextInt(2);
            }
        }
    }

    // Permette di leggere una cella specifica della matrice
    public int getCella(int r, int c){
        if (!isValidIndex(r, c)){
            throw new IndexOutOfBoundsException("Indice non valido per la matrice.");
        }
        return valori[r][c];
    }

    // Permette di scrivere un valore da 0 a 1 su una cella specifica della matrice
    public void setCella(int r, int c, int valore) {
        if (!isValidIndex(r, c)){
            throw new IndexOutOfBoundsException("Indice non valido per la matrice.");
        }
        if (valore != 0 && valore != 1){
            throw new IllegalArgumentException("Il valore deve essere 0 o 1.");
        }
        valori[r][c] = valore;
    }

    // Metodo privato check indici validi
    private boolean isValidIndex(int r, int c) {
        return r >= 0 && r < righe && c >= 0 && c < colonne;
    }
}
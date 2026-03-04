package forme;

import java.util.Objects;

public class Rettangolo extends Forma {
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        if(base <= 0 || altezza <= 0){
            throw new IllegalArgumentException("Base e altezza devono essere maggiori di zero.");
        }
        this.base = base;
        this.altezza = altezza;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    @Override
    public double area() {
        double risultato = base*altezza;
        return risultato;
    }

    @Override
    public double perimetro() {
        double risultato = 2*(base + altezza);
        return risultato;
    }

    // Confronto tra due oggetto
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rettangolo that = (Rettangolo) o;
        return Double.compare(base, that.base) == 0 && Double.compare(altezza, that.altezza) == 0;
    }

    // Crea id per variabile
    @Override
    public int hashCode() {
        return Objects.hash(base, altezza);
    }
}

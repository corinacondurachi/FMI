package Polimorfism_zoo.Carnivor;

import java.util.StringJoiner;

public class Leu extends Carnivor {

    public Leu(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "rage";
    }

    @Override
    public void scoateSunet() {
        System.out.println("leul " + this.sunetSpecific);
    }

    @Override
    public String toString() {
        return super.toString() + "Leu{" +
                "sunetSpecific='" + sunetSpecific + '\'' +
                '}';
    }
}


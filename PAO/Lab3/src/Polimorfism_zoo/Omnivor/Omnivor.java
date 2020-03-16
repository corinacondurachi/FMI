package Polimorfism_zoo.Omnivor;

import Polimorfism_zoo.Animal;

public abstract class Omnivor extends Animal {

    public Omnivor(String nume, int varsta) {
        super(nume, varsta);
        this.tipHrana = "vegetatie_carne";
    }

    @Override
    public void seHraneste() {
        System.out.println(this + " se hraneste cu " + this.tipHrana);
    }
}

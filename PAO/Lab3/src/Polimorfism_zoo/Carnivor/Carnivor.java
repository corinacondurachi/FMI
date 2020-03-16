package Polimorfism_zoo.Carnivor;

import Polimorfism_zoo.Animal;

public abstract class Carnivor extends Animal {

    //public Carnivor(){
    //super(nume:"tom", varsta:5);

    //}

    public Carnivor(String nume, int varsta) {
        super(nume, varsta);
        this.tipHrana = "carne";
    }

    @Override
    public void seHraneste() {

        System.out.println(this + " se hraneste cu " + this.tipHrana);
    }

    @Override
    public String toString() {
        return super.toString() + "Carnivor{" +
                "sunetSpecific='" + sunetSpecific + '\'' +
                '}';
    }
}

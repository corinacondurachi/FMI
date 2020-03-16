package Polimorfism_zoo.Carnivor;

import javax.swing.*;

public class Pisica extends Carnivor {

    public Pisica(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "miau";
    }

    @Override
    public void scoateSunet() {
        System.out.println("pisica " + this.sunetSpecific);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Pisica)
        {
            Pisica pisica = (Pisica) obj; //Cast
            return pisica.getNume().equals(this.getNume());
        }
        else
            return false;
    }

    @Override
    public String getNume()
    {
        return super.getNume();
    }



}

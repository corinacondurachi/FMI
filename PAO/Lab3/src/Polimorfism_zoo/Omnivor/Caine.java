package Polimorfism_zoo.Omnivor;

public class Caine extends Omnivor {

    public Caine(String nume, int varsta){
        super(nume, varsta);
        this.sunetSpecific = "latrat";
    }

    @Override
    public void scoateSunet()
    {
        System.out.println("cainele "+ this.sunetSpecific);
    }
}

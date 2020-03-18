public class Milka extends CandyBox {

    float raza, inaltime;

    public Milka() {
        super();
        this.raza=0;
        this.inaltime=0;
    }

    public Milka(String flavour, String origin, float raza, float inaltime) {

        super(flavour, origin);
        this.raza = raza;
        this.inaltime = inaltime;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + this.getVolume();
    }

    @Override
    public float getVolume() {
        return 3.14f*raza*raza*inaltime;
    }

    public void printMilkaDim() {
        System.out.println("dimensiuni Milka " + this.raza+ this.inaltime);
    }
}

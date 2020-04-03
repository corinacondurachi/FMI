public class Lindt extends CandyBox {
    float lungime, latime, inaltime;

    public Lindt() {
        super();
        this.lungime=0;
        this.latime=0;
        this.inaltime=0;
    }

    public Lindt (String flavour, String origin, float lungime, float latime, float inaltime) {

        super(flavour, origin);
        this.lungime = lungime;
        this.latime = latime;
        this.inaltime = inaltime;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + this.getVolume();
    }

    @Override
   public float getVolume() {
        return lungime*latime*inaltime;
    }

    public void printLindtDim() {
        System.out.println("dimensiuni Lindt " + this.lungime + this.latime + this.inaltime);
    }
}

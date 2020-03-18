public class Heidi extends CandyBox {
    float lungime;

    public Heidi() {
        super();
        this.lungime = 0;
    }

    public Heidi(String flavour, String origin, float lungime) {

        super(flavour, origin);
        this.lungime = lungime;

    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + this.getVolume();
    }

    @Override
    public float getVolume() {
        return lungime * lungime * lungime;
    }

    public void printHeidiDim() {
        System.out.println("dimensiuni Heidi " + this.lungime);
    }

}

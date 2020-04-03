public class CandyBox {

    private String flavour;
    private String origin;

    public CandyBox() {

        this.flavour = null;
        this.origin = null;

    }

    public CandyBox(String flavour, String origin) {
        this.flavour = flavour;
        this.origin = origin;
    }

    public float getVolume() {
        return 0;
    }

    @Override
    public String toString() {
        return "The box from " + origin + " flavour: " + flavour;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  CandyBox)
        {
            CandyBox candyBox = (CandyBox) obj; //Cast
            //verific daca au aceeasi aroma cat si aceeasi origine
            return candyBox.getFlavour().equals(this.getFlavour()) && candyBox.getOrigin().equals(this.getOrigin());
        }
        else
            return false;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getOrigin() {
        return origin;
    }
}

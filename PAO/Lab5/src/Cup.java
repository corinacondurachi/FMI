public class Cup implements BubbleBathable {

    double volume;
    String color;

    public int getLevelOfFragility()
    {
        return Washable.fragile;
    }

    @Override
    public boolean needsWashing(){
        return false;
    }

    @Override
    public void takeBubbleBath() {
        System.out.println("doesn't neeed bubblebath");
    }

    @Override
    public void soak() {

    }

    @Override
    public void wash() {
        needsWashing();
        System.out.println("washing a cup");
    }

    @Override
    public void scrub() {

    }
}

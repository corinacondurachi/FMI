public class Window implements BubbleBathable {

    @Override
    public void soak() {

    }

    @Override
    public void wash() {
        System.out.println("washing a window");
    }

    @Override
    public boolean needsWashing() {
        return false;
    }

    @Override
    public void takeBubbleBath() {

    }

    @Override
    public void scrub() {

    }
}

public interface BubbleBathable extends Soakable, Scrubbale {

    void takeBubbleBath();

    default void wash(){
        Soakable.super.wash();
        System.out.println("default wash in bubble bathing");
    }

}

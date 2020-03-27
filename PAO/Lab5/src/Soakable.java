public interface Soakable extends Washable {

    void soak();
    default void wash(){};
}

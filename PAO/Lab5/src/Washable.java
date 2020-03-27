public interface Washable {

    //public static fina;
    public static final int imperevious = 0;
    int resistant = 1;
    int fragile = 2;
    int explosive = 3;

    //public si abstract(metode) implicit
    public abstract void wash();

    //public
    default boolean needsWashing(){
        System.out.println("by default wash everything");
        return true;
    }

}

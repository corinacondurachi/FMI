package lambda.ex1;

public interface Instrument {
    void play();

    default void clean(){
        System.out.println("in default method");
    }

    static void inStatic(){
        System.out.println("static");
    }
}

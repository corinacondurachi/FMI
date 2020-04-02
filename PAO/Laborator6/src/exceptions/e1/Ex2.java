package exceptions.e1;

public class Ex2 {
    public static void main(String[] args) {

        try {
            int x = m1();
            //nu se executa
            System.out.println(x);
        }
        //putem avea doar try catch sau doar try finally, neaparat una dintre ele trebuie sa existe
        catch (ArithmeticException e) {
            System.out.println("executing catch");
        } finally {
            System.out.println("finally");
        }
    }

    static int m1() {
        int i = 5 / 0;
        System.out.println(i);
        return i;
    }

}

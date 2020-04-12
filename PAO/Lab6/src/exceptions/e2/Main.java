package exceptions.e2;

public class Main {
    private static final int amount = 100;

    public static void main(String[] args) {

        try {
            m1(101);
        } catch (MyCheckedException e) {
            System.out.println(e);
            m2();
        } finally {
            System.out.println("finally");
        }
    }

    static int m1(int x) throws MyCheckedException {
        if (x <= amount) {
            return x;
        } else {
            throw new MyCheckedException(":(");
        }
    }

    static int m2() {
        throw new MyRuntimeException("my rt exception in m2");
    }
}



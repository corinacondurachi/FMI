package exceptions.e1;

//call stack
//m3
//m2
//m1
//main

//try{} catch {} final {}

/* java.Lang -> Throwable
                     Error
                        OutOfMemoryException
                        StackOverflowException
                     Exception
                        RunTimeException -> unchecked exceptions
                                    java.lang, NPE, ArithmeticExceptions, IllegalArgumentException (NumberFormatException), ClassCastException
                        other exceptions -> checked exceptions
                            IOException (java.io), SqlException (java.sql)
                                FileNotFoundException (java.io)

*/


public class Ex1 {
    public static void main(String[] args) throws Exception {

        try {
            m1();
        } catch (NullPointerException e) {
            System.out.println(e);
        } finally {
            System.out.println("in finally");
        }

    }

    static void m1() throws Exception {
        System.out.println("in m1");
        m2();

        throw new Exception();
    }

    static void m2() {
        System.out.println("in m2");
        throw new RuntimeException("exception in m2");
        //m3();
    }

    static void m3() {
        //throws null pointer exception
        System.out.println("in m3");
        Object o = null;
        o.toString();

    }

}

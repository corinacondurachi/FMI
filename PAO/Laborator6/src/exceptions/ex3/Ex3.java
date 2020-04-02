package exceptions.ex3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Ex3 {
    public static void main(String[] args) {
        //resursele de inchid in ordine inversa
        try ( R2 r1 = new R2 ("r1");
              R2 r2 = new R2 ("r2")){
            throw new Exception("exception in try block");
            //System.out.println("in try block");

        }
        catch (Exception e){
            System.out.println(e);
            //suppressed exception
            Throwable[] suppressedExceptions = e.getSuppressed();
            System.out.println(Arrays.toString(suppressedExceptions));
        } finally {
            {
                System.out.println("finally");
            }
        }
    }
}

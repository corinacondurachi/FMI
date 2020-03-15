public class hello2 {

    static  byte defaultValue;

    public static void main(String [] args){

        //byte --> 8-bit integer values
        byte b1=-128; //local variables must be initializes
        System.out.println(b1);

        byte b2=127;
        System.out.println(b2);
        b2++;
        System.out.println(b2++);
        System.out.println(++b2);

        //byte b3=13245; este prea mare
        byte b3=(byte)13245; //cast explicit
        System.out.println(b3);

        System.out.println(defaultValue);
        }
    }

import java.util.Arrays;

public class Ex1 {

        public static void main(String[] args)
        {
            byte[] bytes;
            bytes=new byte[5];
            //daca nu declar lungimea am eroare de compilare
            bytes[0]=-128;
            bytes[4]=127;
            for(byte i=0;i< bytes.length; i++){
                System.out.println(bytes[i]);
            }

            short[] shortArray;
            boolean[] boolArray = new boolean[4];
            int[] ints = new int[]{23,35,11,22};
            int[] ints1=new int[4];
            ints1[0]=35;
            ints1[1]=11;
            ints1[2]=55;
            ints1[3]=22;
            //toate sunt echivalente pt declarare
            int[] anotherIntArray={11,58,69,47};
            System.out.println(anotherIntArray); //afiseaza adresa
            System.out.println(Arrays.toString(anotherIntArray));


        }
}

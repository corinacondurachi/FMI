package Arrays;

import java.util.Random;

public class MyArrayTest {
    public static void main(String[] args) {

        //b
        MyArrayList myarray = new MyArrayList(3);
        myarray.add(1f);
        myarray.add(12f);
        myarray.add(17f);

        System.out.println(myarray.get(0));
        System.out.println(myarray.get(1));
        System.out.println(myarray.get(2));

        //c
        MyArrayList myarray2 = new MyArrayList(5);
        myarray2.add(15);
        myarray2.add(28);
        myarray2.add(26);
        myarray2.add(17);
        myarray2.add(45);

        //Adding 10 random values
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            float x = r.nextFloat();
            myarray2.add(x);
        }

        //Searching 5 random values
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            float value = r.nextFloat();
            System.out.println("Array contains " + value + "? " +  myarray2.contains(value));
        }

        //Removing 5 random values
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            int index = r.nextInt(myarray2.size()-1);
            myarray2.remove(index);
            System.out.println("Index " + index + ": " + myarray2.toString() );
        }

        System.out.println(myarray2.toString());


    }
}

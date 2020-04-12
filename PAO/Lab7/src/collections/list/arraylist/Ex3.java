package collections.list.arraylist;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex3 {
    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(2,3,55,7,99,0,10);
        System.out.println(ints);

        Object[] arrayofInt = ints.toArray();

//        Collections.sort(ints);
//        System.out.println(ints);
        System.out.println(Collections.binarySearch(ints, 99));
        System.out.println(Collections.binarySearch(ints, -1)); //-insertionPoint -1



    }
}

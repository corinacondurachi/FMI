import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        char[] chars = {'j', 'a', 'v', 'a'};
        //for(int i=0;i<chars.length;i++) sunt echivalente
        for (char c : chars) {
            System.out.println(c);
        }
        String value = new String(chars);
        System.out.println(value);
        System.out.println(chars);
        System.out.println();

        int[][] intMatrix = new int[3][3];
        intMatrix[0][0] = 11;
        intMatrix[2][2] = 22;
        for (int[] i : intMatrix) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        char[][] multidimCharArray = {{'c'}, {'2'}, {'j', 'a', 'v', 'a'}};
        System.out.println(multidimCharArray);

        int[][] ints = new int[3][];
        ints[0] = new int[2];
        ints[0][0] = 1;
        ints[0][1] = 2;
        ints[1] = new int[]{4, 5, 47};
        ints[2] = new int[]{33, 55};

        displayArrayValues(ints);
    }


    private static void displayArrayValues(int[][] input) {
        for (int[] i : input) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}

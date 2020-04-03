import java.util.Scanner;

public class CandyBagTest {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Precizati nr de cutii pe care vreti sa le adaugati ");
        int nrCutii = scanner.nextInt();
        CandyBag candy = new CandyBag(nrCutii);
        adaugaCutii(nrCutii, candy);
//        for (int i = 0; (i < candy.cutii.length) && (candy.cutii[i] != null); i++) {
//            CandyBox bombonica = candy.cutii[i];
//            System.out.println(bombonica.toString());
//        }

        //Comparatie
        Heidi ciocolata1 = new Heidi("Alune","UK",7);
        Heidi ciocolata2 = new Heidi("Alune","UK", 7);
        Heidi ciocolata3 = new Heidi("Capsuni","UK", 7);

        System.out.println(ciocolata1.equals(ciocolata2));
        System.out.println(ciocolata1.equals(ciocolata3));

    }

    public static void adaugaCutii(int nr, CandyBag candy) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < nr; i++) {
            int x = scanner.nextInt();
            if (x == 1) {
                Lindt lindt = new Lindt("alune", "Italia", 1, 2, 3);
                candy.adaugaCutie(lindt);
                System.out.println(lindt.toString());
            }
            if (x == 2) {
                Heidi heidi = new Heidi("lapte", "UK", 2);
                candy.adaugaCutie(heidi);
                System.out.println(heidi.toString());
            }
            if (x == 3) {
                Milka milka = new Milka("capsuni", "Franta", 1, 5);
                candy.adaugaCutie(milka);
                System.out.println(milka.toString());
            }
        }
        scanner.close();
    }

}


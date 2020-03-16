package Polimorfism_zoo;

import Polimorfism_zoo.Carnivor.Leu;
import Polimorfism_zoo.Carnivor.Pisica;
import Polimorfism_zoo.Ierbivor.Cal;
import Polimorfism_zoo.Ierbivor.Elefant;
import Polimorfism_zoo.Omnivor.Caine;
import Polimorfism_zoo.Omnivor.Urs;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
//java.lang importat default

public class zooTest {

    public static void main(String[] args) {

        //int nrAnimaleZoo =Integer.valueOf(args[0]);

        Scanner scanner = new Scanner(System.in);
        System.out.println("precizati nr max de animale ce pot fi gazduite la zoo");
        int nrAnimaleZoo = scanner.nextInt();
        scanner.close();

        Zoo zooBucuresti = new Zoo(nrAnimaleZoo);
        adaugaAnimaleLaZoo(zooBucuresti);
        for (int i = 0; i < zooBucuresti.animaleZoo.length && (zooBucuresti.animaleZoo[i] != null); i++) {
            Animal animal = zooBucuresti.animaleZoo[i];
            animal.afiseazaDetalii();
            animal.seHraneste();
            animal.scoateSunet();
        }

        //Comparare
        Pisica pisica1 = new Pisica("Tom",7);
        Pisica pisica2 = new Pisica("Tom", 7);
        System.out.println(pisica1 == pisica2); //Compara continutul, hashCodul genrat e diferit la toate
        // E din clasa Object, deci il facem noi
        System.out.println(pisica1.equals(pisica2));//l-am suprascris
    }

    public static void adaugaAnimaleLaZoo(Zoo zoo) {
        Leu leu = new Leu("Simba", 7);
        zoo.adaugaAnimal(leu);
        Elefant elefant = new Elefant("Eli", 10);
        zoo.adaugaAnimal(elefant);
        Urs urs = new Urs("Fram", 4);
        zoo.adaugaAnimal(urs);
        Pisica pisica = new Pisica("Tom", 2);
        zoo.adaugaAnimal(pisica);
        Caine caine = new Caine("Toto", 3);
        zoo.adaugaAnimal(caine);
        Cal cal = new Cal("Thunder", 3);
        zoo.adaugaAnimal(cal);
    }


}


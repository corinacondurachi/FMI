package imutabilitate;

import java.sql.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Adresa domiciliu = new Adresa("Timisoara","4A");
        Persoana persoana = new Persoana(1,"Teo",domiciliu);

         /*
        incercare alterare valoare camp nume din obiectul persoana
        String este o clasa imutabila, deci valoarea membrului obiectului persoana nu se va modifica
         */

        System.out.println(persoana.getNume().toUpperCase());
        System.out.println(persoana);

        /*
        incercare alterare valoare membru (clasa mutabila) prin modificarea valorilor
        instantei date ca parametru la instantiere clasei imutabile
         */
        String stradaUppercase = domiciliu.getStrada().toUpperCase();
        domiciliu.setStrada(stradaUppercase);
        System.out.println(persoana);

        // incercare alterare continut prin intermediul get-erului
        Adresa adresaDomiciliu = persoana.getAdresa();
        String strada = adresaDomiciliu.getStrada();
        adresaDomiciliu.setStrada(strada.toUpperCase());
        System.out.println(persoana);


    }
}

package Asociere;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Universitate { // has-to-has-a
    private String nume;
    private Departament[] departamente; // Daca dispare univ, nu mai avem departamente deci compozitie

    public Universitate(String nume, Departament[] departamente) {
        this.nume = nume;
        this.departamente = Arrays.copyOf(departamente, departamente.length);
        // copie a obiectului referentiat in parametru
    }

    @Override
    public String toString() {
        return "Universitate{ nume= " + nume + " , departamente= " + Arrays.toString((departamente)) + "}";
    }
}
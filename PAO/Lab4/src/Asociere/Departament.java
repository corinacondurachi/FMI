package Asociere;

import java.util.Arrays;

public class Departament {//has-a
    //profesorii pot exista si fara departament
    private String nume;
    private Profesor[] profesori; //agregare

    public Departament(String nume, Profesor[] profesori) {
        this.nume = nume;
        this.profesori = profesori;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public Profesor[] getProfesori() {
        return profesori;
    }

    public void setProfesori(Profesor[] profesori) {
        this.profesori = profesori;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "nume='" + nume + '\'' +
                ", profesori=" + Arrays.toString(profesori) +
                '}';
    }
}
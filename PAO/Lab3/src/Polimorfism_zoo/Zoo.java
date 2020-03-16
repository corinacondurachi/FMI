package Polimorfism_zoo;

public class Zoo {

    private final int nrMaxAnimale;
    //tb setat in constructor sau aici
    private int indexCurent;
    Animal[] animaleZoo;

    public Zoo(int nrMaxAnimale) {
        if (nrMaxAnimale > 0) {
            this.nrMaxAnimale = nrMaxAnimale;
            this.animaleZoo = new Animal[nrMaxAnimale];
        } else {
            throw new RuntimeException("Nu ati introdus un nr poz");
        }
    }

    public void adaugaAnimal(Animal animal) {
        if (indexCurent < animaleZoo.length) {
            animaleZoo[indexCurent] = animal;
            System.out.println("Adauga animal " + animal.getClass().getSimpleName() + " la pozitia " + indexCurent++);
        }
    }
}

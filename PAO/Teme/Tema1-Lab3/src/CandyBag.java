public class CandyBag {

    private final int nrMaxCutii;
    CandyBox[] cutii;
    private int indexCurent;

    public CandyBag(int nrMaxCutii) {
        if (nrMaxCutii > 0) {
            this.nrMaxCutii = nrMaxCutii;
            this.cutii = new CandyBox[nrMaxCutii];
        } else {
            throw new RuntimeException("Nu ati introdus un nr intreg pozitiv");
        }
    }

    public void adaugaCutie(CandyBox cutie) {
        if (indexCurent < cutii.length) {
            cutii[indexCurent] = cutie;
            System.out.println("Adaugat cutie " + cutie.getClass().getSimpleName() + " la pozitia " + indexCurent++);
        }
    }




}

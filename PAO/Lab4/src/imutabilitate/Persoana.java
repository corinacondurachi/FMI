package imutabilitate;

public final class Persoana {

    // campuri private si final - initializate o sg. data si accesate doar indirect, prin get-eri
    private final int id;
    private final String nume;
    private final Adresa adresa;

    public Persoana(int id, String nume, Adresa adresa) {
        this.id = id;
        this.nume = nume;
        // aceasta implementare este inlocuita de supraincarcarea constructorului clasei Adresa
//        String numeStrada = adresa.getStrada();
//        String numarStrada = adresa.getNumar();
//        Adresa copieAdresa = new Adresa(numeStrada, numarStrada);
//        this.adresa = copieAdresa;
        this.adresa = new Adresa(adresa);
    }

    public int getId() {

        return id;
    }

    public String getNume() {

        return nume;
    }

    public Adresa getAdresa() {
        // inlocuit cu apelul constructorului supraincarcat din Adresa
        //String numeStrada = adresa.getStrada();
//        String numarStrada = adresa.getNumar();
//        Adresa copieAdresa = new Adresa(numeStrada, numarStrada);
//        return copieAdresa;
        return new Adresa(adresa);
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa=" + adresa +
                '}';
    }
}
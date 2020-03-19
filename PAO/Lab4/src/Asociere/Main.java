package Asociere;

public class Main {
    public static void main(String[] args) {
        Profesor profesorPrincipalMate = new Profesor(1, "Popescu");
        Profesor profesorSecundarMate = new Profesor(2, "Ionescu");
        Profesor profesorInfo = new Profesor(3, "Anghel");
        Profesor profesorMateInfo = new Profesor(4, "Petrescu");
        Profesor profesorInfoMate = new Profesor(5, "Andrei");

        Profesor[] profesoriDepartamentMate = new Profesor[]{profesorPrincipalMate, profesorSecundarMate, profesorInfoMate, profesorMateInfo};
        Profesor[] profesoriDepartamentInfo = new Profesor[]{profesorInfo, profesorInfoMate, profesorMateInfo};

        Departament departamentInfo = new Departament("info",profesoriDepartamentInfo);
        //departamentInfo.setProfesori(new Profesor[]{profesorInfo, profesorInfoMate, profesorMateInfo});

        Departament departamentMate = new Departament("mate",profesoriDepartamentMate);
        departamentMate.setProfesori(new Profesor[]{profesorInfoMate, profesorMateInfo, profesorPrincipalMate, profesorSecundarMate});

        Departament[] departamente = new Departament[]{departamentInfo, departamentMate};
        Universitate unibuc = new Universitate("UNIBUC", departamente);
        System.out.println(unibuc);


        departamentInfo = null; // folosim Arrays.copyOf din cauza asta atunci cand ob
        //referentiate de acest paramentru nu vor ajunge null, pentru ca folosim o copie
        System.out.println(unibuc); //nu se pierde referinta catre obiectul asociat
        System.out.println(departamentInfo); //curatat din memorie

    }
}
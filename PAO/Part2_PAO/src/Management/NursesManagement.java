package Management;

import Person.Nurse;

import java.util.ArrayList;

public class NursesManagement {

    ArrayList<Nurse> nurses;

    public NursesManagement() {
        nurses = new ArrayList<Nurse>();
    }

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
        // System.out.println("Nurse added: " + nurse);
    }

    public void showNurses() {
        System.out.println("Nurses currently working at the clinic:");
        for (int i = 0; i < nurses.size(); i++) {
            System.out.println(nurses.get(i));
        }
    }

}

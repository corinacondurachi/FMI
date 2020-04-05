package Person;

import java.util.Comparator;

public class Doctor_Comparator implements Comparator<Doctor> {

    @Override
    public int compare(Doctor d1, Doctor d2) {

        // First compares speciality, then LastName and in case of equality FirstName
        int SpecialityCompare = d1.getSpeciality().compareTo(d2.getSpeciality());
        int Last_NameCompare = d1.getLast_name().compareTo(d2.getLast_name());
        int First_NameCompare = d1.getFirst_name().compareTo(d2.getFirst_name());
        if (SpecialityCompare != 0)
            return SpecialityCompare;
        else {
            if (Last_NameCompare != 0)
                return Last_NameCompare;
            else
                return First_NameCompare;
        }
    }
}

package Person;

import java.util.Comparator;

public class DoctorComparator implements Comparator<Doctor> {

    @Override
    public int compare(Doctor d1, Doctor d2) {

        // First compares speciality, then LastName and in case of equality FirstName
        int SpecialityCompare = d1.getSpeciality().compareTo(d2.getSpeciality());
        int LastNameCompare = d1.getLastName().compareTo(d2.getLastName());
        int FirstNameCompare = d1.getFirstName().compareTo(d2.getFirstName());
        if (SpecialityCompare != 0)
            return SpecialityCompare;
        else {
            if (LastNameCompare != 0)
                return LastNameCompare;
            else
                return FirstNameCompare;
        }
    }
}

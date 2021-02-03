package Hospital_Database.Person;

import java.util.HashMap;

public class AuxiliaryNurse extends Nurse {

    private HashMap< Person, HashMap<String, String> > nurseSchedule = new HashMap<Person, HashMap<String, String>>();

    public AuxiliaryNurse(int ID, String name, int birthdayYear) {


        super(ID, name, birthdayYear);
    }

}

package Hospital_Database.Person;

import java.util.HashMap;

public class ChiefNurse extends Nurse {

    private HashMap<Integer, Integer> medicAuxiliaryNurseRequests = new HashMap<>();

    public ChiefNurse(int ID, String name, int birthdayYear, int careerYears) {
        setID(ID);
        setName(name);
        setBirthdayYear(birthdayYear);
        setCareerYears(careerYears);
    }

    public HashMap<Integer, Integer> getMedicRequests() {
        return medicAuxiliaryNurseRequests;
    }

    public void addMedicRequests(int medicID, int numberOfAuxiliaryNurses ) {
        this.medicAuxiliaryNurseRequests.put(medicID, numberOfAuxiliaryNurses);
    }
}
package Hospital_Database.Person;

import java.util.ArrayList;

public class Medic extends Person {

    // ! Instance variables
    private ArrayList<SpecialistNurse> specialistNurses = new ArrayList<>();
    private ArrayList<AuxiliaryNurse> auxiliaryNurses = new ArrayList<>();
    private ArrayList<Person> pacientsAwaitingDischarge = new ArrayList<>();

    // ! Constructor
    public Medic(int ID, String name, int birthdayYear) {
        super(ID, name, birthdayYear);
    }

    // ! Getters & Setters (We add to the lists, instead of setting them)
    public ArrayList<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    public void attributeAuxiliaryNurse(AuxiliaryNurse auxiliaryNurse) {
        this.auxiliaryNurses.add(auxiliaryNurse);
    }

    public ArrayList<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    public void attributeSpecialistNurse(SpecialistNurse specialistNurse) {
        this.specialistNurses.add(specialistNurse);
    }

    public ArrayList<Person> getPacientsAwaitingDischarge() {
        return pacientsAwaitingDischarge;
    }

    public void addPacientAwaitingDischarge(Person pacientAwaitingDischarge) {
        pacientsAwaitingDischarge.add(pacientAwaitingDischarge);
    }

    // ! Overriden methods
    public String toString() {

        // Adds all associated auxilary nurses IDs to a array list
        ArrayList<Integer> auxiliaryNursesIDs = new ArrayList<>();
        for (AuxiliaryNurse tempAuxiliaryNurse : auxiliaryNurses) {
            auxiliaryNursesIDs.add(tempAuxiliaryNurse.getID());
        }

        // Adds all associated specialist nurses IDs to a array list
        ArrayList<Integer> specialistNursesIDs = new ArrayList<>();
        for (SpecialistNurse tempSpecialistNurse : specialistNurses) {
            specialistNursesIDs.add(tempSpecialistNurse.getID());
        }

        return "ID: " + getID() + "\n" + "Name: " + getName() + "\n" + "Auxiliary Nurses: "
                + auxiliaryNursesIDs.toString() + "\n" + "Specialist Nurses: " + specialistNursesIDs.toString();

    }

    public boolean equals(Object object) {
        if (this == object)
        return true;

        if (object == null)
        return false;

        if (this.getClass() != object.getClass())
        return false;

        Medic medic = (Medic) object;
        return super.getID() == medic.getID(); 
    }

}
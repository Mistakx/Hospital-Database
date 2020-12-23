package Hospital_Database.Person;

public class AuxiliaryNurse extends Nurse {

    // TODO: Maybe add the medic to which the nurse is allocated to.
    // TODO: Since both AuxiliaryNurse and SpecialistNurse can be allocated, 
    // TODO: maybe implement the interface allocation?

    public AuxiliaryNurse(int ID, String name) {
        setID(ID);
        setName(name);
    }

}

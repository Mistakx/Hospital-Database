package Hospital_Database.Person;

import java.util.List;

public class Medic extends Person {
    private List<SpecialistNurse> specialistNurses;
    private List<AuxiliaryNurse> auxiliaryNurses;

    public Medic(int ID, String name, int birthdayYear) {setID(ID); setName(name); setBirthdayYear(birthdayYear);}

    // Auxiliary Nurse
    public List<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    // Specialist Nurse
    public List<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }




    
    
            
           
            
    

}
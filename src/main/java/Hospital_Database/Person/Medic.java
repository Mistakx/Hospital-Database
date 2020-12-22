package Hospital_Database.Person;

import java.util.List;

public class Medic extends Person {
    private List<Pacient> pacients;
    private List<SpecialistNurse> specialistNurses;
    private List<AuxiliaryNurse> auxiliaryNurses;

    public Medic(int ID){setID(ID);}

    // Pacient
    protected void addPacient(Pacient pacient) {
        pacients.add(pacient);
    }

    protected List<Pacient> getPacients() {
        return pacients;
    }
    
    // TODO: If necessary, add a remove pacient

    // Auxiliary Nurse
    public List<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    // Specialist Nurse
    public List<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }




    
    
            
           
            
    

}
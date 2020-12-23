package Hospital_Database.Person;
import java.util.List;

public class SpecialistNurse extends Nurse {
    private List<Pacient> pacients;
    
    public SpecialistNurse(int ID, String name) {
        setID(ID);
        setName(name);
    }


    // Pacients
    protected void addPacient(Pacient pacient){pacients.add(pacient);}

    protected List<Pacient> getPacients(){return pacients;}


} 

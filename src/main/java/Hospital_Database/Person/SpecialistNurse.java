package Hospital_Database.Person;
import java.util.List;

public class SpecialistNurse extends Nurse {
    private List<Pacient> pacients;
    
    public SpecialistNurse(int ID) {setID(ID);}

    // Pacients
    protected void addPacient(Pacient pacient){pacients.add(pacient);}

    protected List<Pacient> getPacients(){return pacients;}
    // TODO: If necessary, add a remove pacient


} 

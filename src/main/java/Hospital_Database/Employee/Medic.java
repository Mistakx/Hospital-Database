package Hospital_Database.Employee;

import Hospital_Database.Pacient;
import java.util.List;

public class Medic extends Employee {
    
    private List<Pacient> pacients;
    
    protected void addPacient(Pacient pacient){pacients.add(pacient);}
    protected List<Pacient> getPacients(){return pacients;}
    // TODO: If necessary, add a remove pacient

    
    
            
           
            
    

}
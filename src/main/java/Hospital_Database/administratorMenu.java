package Hospital_Database;

import java.util.List;
import java.util.Scanner;
import Hospital_Database.Employee.ChiefNurse;
import Hospital_Database.Employee.Employee;
import Hospital_Database.Employee.Medic;
import Hospital_Database.Employee.SpecialistNurse;
import java.util.HashMap;

public class administratorMenu {
    final private int YEARS_TILL_PROMOTION = 20;
    private List<Pacient> pacients; // TODO: Move to interface
    private HashMap<Integer, Employee> employees;
    
    Scanner scannerObject = new Scanner(System.in);  // Create a Scanner object

    public void addMedic(){
        Medic newMedic = new Medic(); // TODO: Type: medic or employee?
        employees.put(employees.size() + 1, newMedic);
    }
    public void addSpecialistNurse(){
        // TODO
    }
    public void addAuxiliaryNurse(){
        // TODO
    }
    
    public void addNewPacient(){
        System.out.println("Ano de nascimento do novo paciente: ");
        String newPacientBirthdayYear = scannerObject.nextLine();  // Read user input
        int newPacientID = pacients.size() + 1;
        
        Pacient newPacient = new Pacient(newPacientID, Integer.parseInt(newPacientBirthdayYear));
        pacients.add(newPacient);
    }
    
    public ChiefNurse promoteSpecialistNurse(SpecialistNurse nurse){
        // TODO: Test

        int careerYears = nurse.getCareerYears();

        if (careerYears >= YEARS_TILL_PROMOTION) {
            ChiefNurse promotedNurse = new ChiefNurse();
            promotedNurse.setCareerYears(careerYears);
            promotedNurse.setName(nurse.getName());
            System.out.println("O enfermeiro não têm anos de carreira suficientes.");
        }

        return promoteSpecialistNurse(nurse);
    }


}
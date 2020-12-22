package Hospital_Database;

import java.util.List;
import java.util.Scanner;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Pacient;
import Hospital_Database.Person.SpecialistNurse;

public class administratorMenu {

    Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

    public void addMedic() {
        List<Medic> medics = Hospital.getMedics();

        // ID
        Medic newMedic = new Medic(Hospital.getLastIDAttributed() + 1);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
        
        medics.add(newMedic);
    }

    public void addSpecialistNurse() {
        List<Employee> employees = Hospital.getEmployees();
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(employees.size());
        employees.add(newSpecialistNurse);
        // TODO: Maybe add specialist nurse to specific list?
    }

    public void addAuxiliaryNurse() {
        List<Employee> employees = Hospital.getEmployees();
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(employees.size());
        employees.add(newAuxiliaryNurse);
        // TODO: Maybe add auxiliary nurse to specific list?
    }

    public void addNewPacient() {
        List<Pacient> pacients = Hospital.getPacients();

        System.out.println("Ano de nascimento do novo paciente: ");
        String newPacientBirthdayYear = scannerObject.nextLine(); // Read user input
        int newPacientID = pacients.size() + 1;

        Pacient newPacient = new Pacient(newPacientID, Integer.parseInt(newPacientBirthdayYear));
        pacients.add(newPacient);
    }

    public ChiefNurse promoteSpecialistNurse(SpecialistNurse nurse) {
        // TODO: Test

        int careerYears = nurse.getCareerYears();
        int YEARS_TILL_PROMOTION = Hospital.getYEARS_TILL_PROMOTION();

        if (careerYears >= YEARS_TILL_PROMOTION) {
            ChiefNurse promotedNurse = new ChiefNurse();
            promotedNurse.setCareerYears(careerYears);
            promotedNurse.setName(nurse.getName());
            System.out.println("O enfermeiro não têm anos de carreira suficientes.");
        }

        return promoteSpecialistNurse(nurse);
    }

}
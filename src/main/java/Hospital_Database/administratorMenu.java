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

        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();
        String specialistNurseName;

        // Name
        System.out.println("Insira o nome do enfermeiro especialista: ");
        specialistNurseName = scannerObject.nextLine();

        // ID
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(Hospital.getLastIDAttributed() + 1,
                specialistNurseName);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);

        specialistNurses.add(newSpecialistNurse);

    }

    public void addAuxiliaryNurse() {
        List<AuxiliaryNurse> auxiliaryNurses = Hospital.getAuxiliaryNurses();
        String auxiliaryNurseName;

        // Name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        auxiliaryNurseName = scannerObject.nextLine();

        // ID
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(Hospital.getLastIDAttributed(), auxiliaryNurseName);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);

        auxiliaryNurses.add(newAuxiliaryNurse);

    }

    public void addNewPacient() {
        List<Pacient> pacients = Hospital.getPacients();

        System.out.println("Ano de nascimento do novo paciente: ");
        String newPacientBirthdayYear = scannerObject.nextLine(); // Read user input
        int newPacientID = pacients.size() + 1;

        Pacient newPacient = new Pacient(newPacientID, Integer.parseInt(newPacientBirthdayYear));
        pacients.add(newPacient);
    }

    public void promoteSpecialistNurse() {
        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();

        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = Integer.parseInt(scannerObject.nextLine());

        // If ID exists
        if (specialistNurseID <= Hospital.getLastIDAttributed()) {

            // Check if a specialist nurse with the ID exists
            boolean specialistNurseExists = false;

            for (int i = 0; i < specialistNurses.size(); i++) {

                SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);

                if (tempSpecialistNurse.getID() == specialistNurseID) {
                    specialistNurseExists = true;

                    // Promote specialist nurse to chief nurse
                    

                    int nurseCareerYears = tempSpecialistNurse.getCareerYears();
                    String nurseName = tempSpecialistNurse.getName();
                    int nurseID = tempSpecialistNurse.getID();
                    int YEARS_TILL_PROMOTION = Hospital.getYEARS_TILL_PROMOTION();
            
                    if (nurseCareerYears >= YEARS_TILL_PROMOTION) {
                        ChiefNurse promotedNurse = new ChiefNurse(nurseID, nurseName, nurseCareerYears);

                    } else {
                        System.out.println("O enfermeiro especialista não tem anos de carreira suficientes.");
                    }
            
                    return promoteSpecialistNurse(nurse);
                    break;
                }

            }

            if (!specialistNurseExists) {
                System.out.println("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
            }

        }

        // If ID doesn't exist
        else {
            System.out.println("Não existe nenhuma pessoa com o ID " + specialistNurseID + ".");
        }


    }

}
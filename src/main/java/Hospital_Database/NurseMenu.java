/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.util.List;
import java.util.Scanner;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.SpecialistNurse;

/**
 *
 * @author mistakx
 */

public class NurseMenu extends Hospital {
    static Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

    

    public void nurseMenuUserInterface() {

        while (true) {
    
            System.out.println("Selecione uma opção!");
            System.out.println("1 - Listar enfermeiros de um médico.");
            System.out.println("2 - Listar pacientes a aguardar curativo.");
            System.out.println("3 - Atribuir enfermeiro-especialista a médico.");
            System.out.println("4 - Aplicar curativo a um paciente.");
            System.out.println("0 - Voltar ao menu anterior.");

            boolean exitMenuUserInterface = false;
            int option = 0;
            option = scannerObject.nextInt();

            switch (option) {
                case 1:
                    listMedicNurses();
                    break;
                case 2:
                    listPacientsWaitingForCure();
                    break;
                case 3:
                    attributeSpecialistNurseToMedic();
                    break;
                case 4:
                    applyCureToPacient();
                    break;
                case 5:
                    exitMenuUserInterface = true;
                    scannerObject.close();
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;

            }

            if (exitMenuUserInterface) {break;}
        }

    }

    private void listMedicNurses() {
        List<Medic> medics = Hospital.getMedics();

        int medicID;
        System.out.println("Nº do médico: ");
        medicID = Integer.parseInt(scannerObject.nextLine());

        // If ID exists
        if (medicID <= Hospital.getLastIDAttributed()) {

            // Check if a medic with the ID exists
            boolean medicExists = false;

            for (int i = 0; i < medics.size(); i++) {

                Medic tempMedic = medics.get(i);

                if (tempMedic.getID() == medicID) {
                    medicExists = true;
                    List<AuxiliaryNurse> medicAuxiliaryNurses = tempMedic.getAuxiliaryNurses();
                    List<SpecialistNurse> medicSpecialistNurses = tempMedic.getSpecialistNurses();

                    System.out.println("Médico (ID: " + medicID + ").\n");
                    System.out.println("Enfermeiros auxiliares\n");

                    for (int y = 0; y < medicAuxiliaryNurses.size(); y++) {

                        AuxiliaryNurse tempAuxiliaryNurse = medicAuxiliaryNurses.get(y);
                        System.out.println(tempAuxiliaryNurse.getID() + ": " + tempAuxiliaryNurse.getName());

                    }

                    System.out.println("Enfermeiros especialistas\n");

                    for (int y = 0; y < medicSpecialistNurses.size(); y++) {

                        SpecialistNurse tempSpecialistNurse = medicSpecialistNurses.get(y);
                        System.out.println(tempSpecialistNurse.getID() + ": " + tempSpecialistNurse.getName());

                    }

                    break;
                }

            }

            if (!medicExists) {
                System.out.println("Não existe um médico com o ID " + medicID + ".");
            }

        }

        // If ID doesn't exist
        else {
            System.out.println("Não existe nenhuma pessoa com o ID " + medicID + ".");
        }

    }

    private void listPacientsWaitingForCure() {
        // TODO
    }

    private void attributeSpecialistNurseToMedic() {

        // ! Specialist Nurse
        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();
        int specialistNurseID;

        System.out.println("ID do enfermeiro especialista a atribuir: ");
        specialistNurseID = Integer.parseInt(scannerObject.next());

        boolean specialistNurseExists = false;
        SpecialistNurse specialistNurse; // TODO: Bernardo - Talk about this situation

        // If specialist nurse ID exists
        if (specialistNurseID <= Hospital.getLastIDAttributed()) {

            // Check if a specialist nurse with the ID exists
            for (int i = 0; i < specialistNurses.size(); i++) {

                SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);

                if (tempSpecialistNurse.getID() == specialistNurseID) {
                    specialistNurseExists = true;
                    specialistNurse = tempSpecialistNurse;
                    break;
                }

            }

            if (!specialistNurseExists) {
                System.out.println("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
            }

        }
        // If specialist nurse ID doesn't exist
        else {
            System.out.println("Não existe nenhuma pessoa com o ID " + specialistNurseID + ".");
        }

        // ! Medic
        if (specialistNurseExists) {
            List<Medic> medics = Hospital.getMedics();
            int medicID;

            System.out.println("ID do médico: ");
            medicID = Integer.parseInt(scannerObject.next());

            // If medic ID exists
            if (medicID <= Hospital.getLastIDAttributed()) {

                // Check if a medic with the ID exists
                boolean medicExists = false;

                for (int i = 0; i < medics.size(); i++) {

                    Medic tempMedic = medics.get(i);

                    if (tempMedic.getID() == medicID) {
                        medicExists = true;

                        List<SpecialistNurse> medicSpecialistNurses = tempMedic.getSpecialistNurses();
                        medicSpecialistNurses.add(specialistNurse);
                        break;

                    }

                }

                if (!medicExists) {
                    System.out.println("Não existe um médico com o ID " + medicID + ".");
                }

            }
            // If medic ID doesn't exist
            else {
                System.out.println("Não existe nenhuma pessoa com o ID " + medicID + ".");
            }

        }

    }

    private void applyCureToPacient() {
        // TODO
    }

}

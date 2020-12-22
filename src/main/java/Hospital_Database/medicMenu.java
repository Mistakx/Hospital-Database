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
public class medicMenu {

    Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

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

    private void attributeSpecialistNurseToMedic() 
    {
        // TODO: DALHE HUGO
        int specialistNurseNumber;
        int medicNumber;

        System.out.println("Nº do enfermeiro especialista a atribuir: ");
        specialistNurseNumber = Integer.parseInt(scannerObject.nextLine());
        System.out.println("Nº do médico: ");
        medicNumber = Integer.parseInt(scannerObject.nextLine());
        
        Medic medic;
        medic.getAuxiliaryNurses().add(e);

        

    }

}

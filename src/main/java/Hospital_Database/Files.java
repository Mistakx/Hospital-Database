/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;

/**
 *
 * @author mistakx
 */

public class Files {

    public static void populateHospital(String fileLocation, Hospital hospital) {

        // At the beginning of the program execution, reads a file and populates the
        // hospital accordingly.
        // The hospital last attributed ID is also synced with the file's max ID.

        List<Medic> medics = hospital.getMedics();
        List<AuxiliaryNurse> auxiliaryNurses = hospital.getAuxiliaryNurses();
        List<SpecialistNurse> specialistNurses = hospital.getSpecialistNurses();
        List<SpecialistNurse> chiefNurses = hospital.getChiefNurses();

        Queue<Person> pacientsQueue = hospital.getPacientQueue();

        File file = new File(fileLocation);

        try {
            file.createNewFile(); // Creates file if it doesn't already exist
            Scanner scanner = new Scanner(file);

            // Each line from the populate file is a different person
            while (scanner.hasNextLine()) {

                // Reads the person's class from the file
                String person = scanner.nextLine();
                String[] parsedPerson = person.split(",");
                String personClass = parsedPerson[0];

                // Decides on what to do, based on the person's class
                switch (personClass) {
                    case "Person":

                        int pacientID = Integer.parseInt(parsedPerson[1]);
                        String pacientName = parsedPerson[2];
                        int pacientBirthdayYear = Integer.parseInt(parsedPerson[3]);
                        double pacientTemperature = Double.parseDouble(parsedPerson[4]);
                        double pacientBloodCellLevels = Double.parseDouble(parsedPerson[5]);
                        boolean pacientGastrointestinalSymptoms = Boolean.parseBoolean(parsedPerson[6]);

                        Person newPerson = new Person(pacientID, pacientName, pacientBirthdayYear, pacientTemperature,
                                pacientBloodCellLevels, pacientGastrointestinalSymptoms);

                        pacientsQueue.add(newPerson);

                        // Increments the hospital's last attributed ID, if necessary
                        if (pacientID > Hospital.getLastIDAttributed()) {
                            Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
                        }

                        break;

                    case "Medic":

                        int medicID = Integer.parseInt(parsedPerson[1]);
                        String medicName = parsedPerson[2];
                        int medicBirthdayYear = Integer.parseInt(parsedPerson[3]);

                        Medic newMedic = new Medic(medicID, medicName, medicBirthdayYear);

                        medics.add(newMedic);

                        // Increments the hospital's last attributed ID, if necessary
                        if (medicID > Hospital.getLastIDAttributed()) {
                            Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
                        }

                        break;

                    case "AuxiliaryNurse":

                        int auxiliaryNurseID = Integer.parseInt(parsedPerson[1]);
                        String auxiliaryNurseName = parsedPerson[2];
                        int auxiliaryNurseBirthdayYear = Integer.parseInt(parsedPerson[3]);
                        int auxiliaryNurseCareerYears = Integer.parseInt(parsedPerson[4]);

                        AuxiliaryNurse newAuxiliary = new AuxiliaryNurse(auxiliaryNurseID, auxiliaryNurseName,
                                auxiliaryNurseBirthdayYear, auxiliaryNurseCareerYears);

                        auxiliaryNurses.add(newAuxiliary);

                        // Increments the hospital's last attributed ID, if necessary
                        if (auxiliaryNurseID > Hospital.getLastIDAttributed()) {
                            Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
                        }

                        break;

                    case "SpecialistNurse":
                        int specialistNurseID = Integer.parseInt(parsedPerson[1]);
                        String specialistNurseName = parsedPerson[2];
                        int specialistNurseBirthdayYear = Integer.parseInt(parsedPerson[3]);
                        int specialistNurseCareerYears = Integer.parseInt(parsedPerson[4]);

                        SpecialistNurse newSpecialistNurse = new SpecialistNurse(specialistNurseID, specialistNurseName,
                                specialistNurseBirthdayYear, specialistNurseCareerYears);

                        specialistNurses.add(newSpecialistNurse);

                        // Increments the hospital's last attributed ID, if necessary
                        if (specialistNurseID > Hospital.getLastIDAttributed()) {
                            Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
                        }

                        break;

                    case "ChiefNurse":
                        int chiefNurseID = Integer.parseInt(parsedPerson[1]);
                        String chiefNurseName = parsedPerson[2];
                        int chiefNurseBirthdayYear = Integer.parseInt(parsedPerson[3]);
                        int chiefNurseCareerYears = Integer.parseInt(parsedPerson[4]);

                        SpecialistNurse newChiefNurse = new SpecialistNurse(chiefNurseID, chiefNurseName,
                                chiefNurseBirthdayYear, chiefNurseCareerYears);

                        chiefNurses.add(newChiefNurse);

                        // Increments the hospital's last attributed ID, if necessary
                        if (chiefNurseID > Hospital.getLastIDAttributed()) {
                            Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);
                        }

                        break;

                    default:
                        break;
                }

            }

            scanner.close();

        } catch (IOException exception) {
            ClearConsole.clearConsole();
            System.out.println(exception.getMessage());
            Scanner scanner = new Scanner(System.in);
            scanner.next();
            scanner.close();
        }

    }
}

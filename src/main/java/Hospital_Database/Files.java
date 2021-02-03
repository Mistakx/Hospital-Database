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

        List<Medic> medics = hospital.getMedics();
        List<AuxiliaryNurse> auxiliaryNurses = hospital.getAuxiliaryNurses();
        List<SpecialistNurse> specialistNurses = hospital.getSpecialistNurses();
        List<SpecialistNurse> chiefNurses = hospital.getChiefNurses();

        Queue<Person> pacientsQueue = hospital.getPacientQueue();

        File file = new File(fileLocation);

        try {
            file.createNewFile(); // Creates file if it doesn't already exist
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String person = scanner.nextLine();
                String[] parsedPerson = person.split(",");
                String personClass = parsedPerson[0];

                switch (personClass) {
                    case "Person":
                        Person newPerson = new Person(Integer.parseInt(parsedPerson[1]), parsedPerson[2],
                                Integer.parseInt(parsedPerson[3]), Integer.parseInt(parsedPerson[4]),
                                Integer.parseInt(parsedPerson[5]), Boolean.parseBoolean(parsedPerson[6]));

                        pacientsQueue.add(newPerson);

                        break;

                    case "Medic":
                        Medic newMedic = new Medic(Integer.parseInt(parsedPerson[1]), parsedPerson[2],
                                Integer.parseInt(parsedPerson[3]));

                        medics.add(newMedic);
                        break;

                    case "AuxiliaryNurse":
                        AuxiliaryNurse newAuxiliary = new AuxiliaryNurse(Integer.parseInt(parsedPerson[1]),
                                parsedPerson[2], Integer.parseInt(parsedPerson[3]));

                        auxiliaryNurses.add(newAuxiliary);
                        break;

                    case "SpecialistNurse":
                        SpecialistNurse newSpecialistNurse = new SpecialistNurse(Integer.parseInt(parsedPerson[1]),
                                parsedPerson[2], Integer.parseInt(parsedPerson[3]));

                        specialistNurses.add(newSpecialistNurse);
                        break;

                    case "ChiefNurse":
                        SpecialistNurse newChiefNurse = new SpecialistNurse(Integer.parseInt(parsedPerson[1]),
                                parsedPerson[2], Integer.parseInt(parsedPerson[3]));

                        chiefNurses.add(newChiefNurse);
                        break;

                    default:
                        break;
                }

            }

            scanner.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}

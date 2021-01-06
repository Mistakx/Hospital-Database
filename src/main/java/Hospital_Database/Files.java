/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;

/**
 *
 * @author mistakx
 */

public class Files {

    public static void readAndParseFile(String fileLocation) {

        List<Medic> Medics;
        List<AuxiliaryNurse> auxiliaryNurses;
        List<SpecialistNurse> specialistNurses;
        List<ChiefNurse> chiefNurses;

        try {

            File file = new File(fileLocation);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                // Pessoa
                // Person class 0
                // private int ID; 1
                // private String name; 2
                // private int birthdayYear; 3
                // int temperature; 4
                // int whiteBloodCellLevels; 5
                // boolean gastrointestinalSymptoms; 6

                // Classe, id,


                String person = scanner.nextLine();
                String[] parsedPerson = person.split(",");
                String personClass = parsedPerson[0];

                switch (personClass) {
                    case "Person":
                        Person newPerson = new Person(Integer.parseInt(parsedPerson[1]), parsedPerson[2],
                                Integer.parseInt(parsedPerson[3]), Integer.parseInt(parsedPerson[4]),
                                Integer.parseInt(parsedPerson[5]), Boolean.parseBoolean(parsedPerson[6]));
                                
                        

                        break;

                    case "Medic":
                        
                        break;

                    case "AuxiliaryNurse":
                        break;

                    case "SpecialistNurse":
                        break;

                    case "ChiefNurse":
                        break;

                    default:

                        break;
                }

            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}

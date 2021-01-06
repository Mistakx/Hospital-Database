package Hospital_Database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;

public class Hospital {
    
    // Class-Based singleton implementation

    private static Hospital INSTANCE;
    
    private Hospital(String hospitalDatabaseFilePath) {Files.readAndParseFile(hospitalDatabaseFilePath);}
    
    public static Hospital getInstance(String hospitalDatabaseFilePath) {
        if (INSTANCE == null) {
            INSTANCE = new Hospital(hospitalDatabaseFilePath);
        }
        
        return INSTANCE;
    }

    private static int lastIDAttributed = 0;

    final private int NECESSARY_YEARS_FOR_PROMOTION = 20;

    // ! Hospital employees
    private List<Medic> Medics = new ArrayList<>();
    private List<AuxiliaryNurse> auxiliaryNurses = new ArrayList<>();
    private List<SpecialistNurse> specialistNurses = new ArrayList<>();
    private List<ChiefNurse> chiefNurses = new ArrayList<>();

    // ! Queues
    private Queue<Person> pacientsQueue = new LinkedList<>(); // TODO: Add pacients to queue


    public int getNECESSARY_YEARS_FOR_PROMOTION() {
        return NECESSARY_YEARS_FOR_PROMOTION;
    }


    // * ID
    public static int getLastIDAttributed() {
        return lastIDAttributed;
    }

    public static void setLastIDAttributed(int lastID) {
        Hospital.lastIDAttributed = lastID;
    }

    // * Hospital employees
    public List<Medic> getMedics() {
        return Medics;
    }

    public List<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    public List<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    public List<ChiefNurse> getChiefNurses() {
        return chiefNurses;
    }

    // * Queues
    public Queue<Person> getPacientQueue() {
        return pacientsQueue;
    }

}

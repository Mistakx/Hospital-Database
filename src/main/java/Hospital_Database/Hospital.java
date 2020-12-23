package Hospital_Database;

import Hospital_Database.Person.Pacient;
import Hospital_Database.Person.SpecialistNurse;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Hospital {

    final private static int YEARS_TILL_PROMOTION = 20;

    // ! IDs start at 0 by design
    private static int lastIDAttributed = 0; // TODO: Maybe add a list with all the people? 
    private static List<Pacient> pacients = Collections.emptyList();
    private static List<Medic> medics = Collections.emptyList();
    private static List<SpecialistNurse> specialistNurses = Collections.emptyList();
    private static List<AuxiliaryNurse> auxiliaryNurses = Collections.emptyList();
    private static List<ChiefNurse> chiefNurses = Collections.emptyList();

    // Todo: What if method isn't static?

    // Years till promotion
    public static int getYEARS_TILL_PROMOTION() {
        return YEARS_TILL_PROMOTION;
    }

    // Chief nurses
    public static List<ChiefNurse> getChiefNurses() {
        return chiefNurses;
    }

    // Auxiliary nurses
    public static List<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    // Specialist nurses
    public static List<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    // ID
    public static int getLastIDAttributed() {
        return lastIDAttributed;
    }

    public static void setLastIDAttributed(int lastID) {
        Hospital.lastIDAttributed = lastID;
    }

    // Pacients
    public static List<Pacient> getPacients() {
        return pacients;
    }

    // Medics
    public static List<Medic> getMedics() {
        return medics;
    }
    

    

}
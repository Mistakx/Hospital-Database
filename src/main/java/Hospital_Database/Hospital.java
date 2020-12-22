package Hospital_Database;

import Hospital_Database.Person.Pacient;
import Hospital_Database.Person.Medic;

import java.util.List;

public class Hospital {

    final private static int YEARS_TILL_PROMOTION = 20;

    // ! IDs start at 0 by design
    private static int lastIDAttributed = 0; // TODO: Maybe add a list with all the people?
    private static List<Pacient> pacients;
    private static List<Medic> medics;

    // Todo: What if method isn't static?

    // Years till promotion
    public static int getYEARS_TILL_PROMOTION() {
        return YEARS_TILL_PROMOTION;
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
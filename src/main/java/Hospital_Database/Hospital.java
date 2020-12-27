package Hospital_Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Pacient;
import Hospital_Database.Person.SpecialistNurse;

public class Hospital {

    final private static int YEARS_TILL_PROMOTION = 20;

    // ! IDs start at 0 by design
    private static int lastIDAttributed = 0; // TODO: Maybe add a list with all the people?
    private static List<Pacient> pacients = new ArrayList<>();
    private static List<Medic> medics = new ArrayList<>();
    private static List<SpecialistNurse> specialistNurses = new ArrayList<>();
    private static List<AuxiliaryNurse> auxiliaryNurses = new ArrayList<>();
    private static List<ChiefNurse> chiefNurses = new ArrayList<>();

    // ! Queues
    private static Queue<Pacient> pacientsQueue = new LinkedList<>(); // TODO: Add pacients to queue
    private static Queue<Pacient> pacientsAwaitingDischargeQueue = new LinkedList<>(); // TODO: Add pacients to queue

    private Hospital() {
    }

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

    // Pacient queue
    public static Queue<Pacient> getPacientQueue() {
        return pacientsQueue;
    }

    // Pacient awaiting discharge queue
    public static Queue<Pacient> getPacientsAwaitingDischargeQueue() {
        return pacientsAwaitingDischargeQueue;
    }

}

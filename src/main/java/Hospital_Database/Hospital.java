package Hospital_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.Exceptions.NotEnoughCareerYearsException;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Nurse;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;

public class Hospital {

    // ! Class-based singleton implementation
    private static Hospital INSTANCE;
    private Scanner scannerObject = new Scanner(System.in);

    private Hospital() {
    }

    public static Hospital getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Hospital();
        }

        return INSTANCE;
    }

    // ! Instance variables
    private static int lastIDAttributed = 0;

    final private int NECESSARY_YEARS_FOR_PROMOTION = 20;

    private List<Medic> medics = new ArrayList<>();
    private List<AuxiliaryNurse> auxiliaryNurses = new ArrayList<>();
    private List<SpecialistNurse> specialistNurses = new ArrayList<>();
    private List<SpecialistNurse> chiefNurses = new ArrayList<>();

    private Queue<Person> pacientsQueue = new LinkedList<>();
    private HashMap<Medic, Integer> auxiliaryRequests = new HashMap<>();

    // ! Getters and setters
    public int getNECESSARY_YEARS_FOR_PROMOTION() {
        return NECESSARY_YEARS_FOR_PROMOTION;
    }

    public static int getLastIDAttributed() {
        return lastIDAttributed;
    }

    public static void setLastIDAttributed(int lastID) {
        Hospital.lastIDAttributed = lastID;
    }

    public List<Medic> getMedics() {
        return medics;
    }

    public List<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    public List<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    public List<SpecialistNurse> getChiefNurses() {
        return chiefNurses;
    }

    public Queue<Person> getPacientQueue() {
        return pacientsQueue;
    }

    // ! Populate
    protected void populate(String hospitalDatabaseFilePath) {
        Files.populateHospital(hospitalDatabaseFilePath, this);
    }

    // ! Administrator menu
    public void addMedic() {

        System.out.println("Nome do médico: ");
        String name;
        name = scannerObject.next();

        System.out.println("Ano de nascimento do médico: ");
        int birthdayYear;
        birthdayYear = scannerObject.nextInt();

        Medic newMedic = new Medic(lastIDAttributed + 1, name, birthdayYear);
        lastIDAttributed++;

        medics.add(newMedic);

    }

    public void addSpecialistNurse() {

        // Name
        System.out.println("Nome do enfermeiro especialista: ");
        String name = "";
        name = scannerObject.nextLine();

        // Birthday year
        System.out.println("Ano de nascimento do enfermeiro especialista: ");
        int birthdayYear;
        birthdayYear = scannerObject.nextInt();

        SpecialistNurse newSpecialistNurse = new SpecialistNurse(lastIDAttributed + 1, name, birthdayYear);
        lastIDAttributed++;

        specialistNurses.add(newSpecialistNurse);

    }

    public void addAuxiliaryNurse() {

        // Name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        String name = "";
        name = scannerObject.nextLine();

        // Birthday year
        System.out.println("Ano de nascimento do enfermeiro auxiliar: ");
        int birthdayYear;
        birthdayYear = scannerObject.nextInt();

        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(lastIDAttributed + 1, name, birthdayYear);
        lastIDAttributed++;

        auxiliaryNurses.add(newAuxiliaryNurse);

    }

    public void addNewPacient() { // Adds new pacient to the hospital

        // Input pacient name
        System.out.println("Insira o nome do paciente: ");
        String name = "";
        name = scannerObject.nextLine();

        // Input pacient birthday year
        System.out.println("Ano de nascimento do novo paciente: ");
        int birthdayYear = scannerObject.nextInt();

        // Create pacient, add pacient to list, and add 1 to the last ID generated
        Person newPacient = new Person(lastIDAttributed, name, birthdayYear);
        pacientsQueue.add(newPacient);
        lastIDAttributed++;


    }

    public void promoteSpecialistNurseToChief() throws NotEnoughCareerYearsException, IDNotFoundException { // Promotes a specialist nurse to chief nurse

        // Input ID of specialist nurse to be promoted
        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = Integer.parseInt(scannerObject.nextLine());

        // Check if a specialist nurse with the ID exists
        boolean specialistNurseExists = false;

        for (SpecialistNurse tempSpecialistNurse : specialistNurses) {

            // If specialist nurse exists
            if (tempSpecialistNurse.getID() == specialistNurseID) {
                specialistNurseExists = true;

                int nurseCareerYears = tempSpecialistNurse.getCareerYears();

                // Promote specialist nurse to chief nurse
                if (nurseCareerYears >= NECESSARY_YEARS_FOR_PROMOTION) {

                    chiefNurses.add(tempSpecialistNurse);
                    specialistNurses.remove(tempSpecialistNurse);

                } else {
                    throw new NotEnoughCareerYearsException("O enfermeiro especialista não tem anos de carreira suficientes.");
                }

                break;
            }

        }

        if (!specialistNurseExists) {
            throw new IDNotFoundException("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        }

    }

    public void progressNursesCareerYears() {

        // Specialist nurses
        for (int i = 0; i < specialistNurses.size(); i++) {
            SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
            tempSpecialistNurse.setCareerYears(tempSpecialistNurse.getCareerYears() + 1);
        }

        // Auxiliary nurses
        for (int i = 0; i < auxiliaryNurses.size(); i++) {
            AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
            tempAuxiliaryNurse.setCareerYears(tempAuxiliaryNurse.getCareerYears() + 1);
        }

        // Chief nurses
        for (int i = 0; i < chiefNurses.size(); i++) {
            SpecialistNurse tempChiefNurse = chiefNurses.get(i);
            tempChiefNurse.setCareerYears(tempChiefNurse.getCareerYears() + 1);
        }

        System.out.println("Foi adicionado 1 ano de carreira a todos os enfermeiros existentes.");
        scannerObject.next();
        // TODO: Pause

    }

    public void listNurses() {

        // TODO: List what medic the nurse is allocated to

        System.out.println("Enfermeiros especialista");
        // Specialist nurses
        for (int i = 0; i < specialistNurses.size(); i++) {
            SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
            int nurseCareerYears = tempSpecialistNurse.getCareerYears();
            String nurseName = tempSpecialistNurse.getName();
            int nurseID = tempSpecialistNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

        System.out.println("Enfermeiros auxiliares");
        // Auxiliary nurses
        for (int i = 0; i < auxiliaryNurses.size(); i++) {
            AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
            int nurseCareerYears = tempAuxiliaryNurse.getCareerYears();
            String nurseName = tempAuxiliaryNurse.getName();
            int nurseID = tempAuxiliaryNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

        System.out.println("Enfermeiros chefe");
        // Chief nurses
        for (int i = 0; i < chiefNurses.size(); i++) {
            SpecialistNurse tempChiefNurse = chiefNurses.get(i);
            int nurseCareerYears = tempChiefNurse.getCareerYears();
            String nurseName = tempChiefNurse.getName();
            int nurseID = tempChiefNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

    }

    public void listMedics() {

        for (int i = 0; i < medics.size(); i++) {
            Medic tempMedic = medics.get(i);
            System.out.println(tempMedic.toString());
        }

    }

    public void listRequestsForAuxiliaryNurses() {
        // TODO
    }

    // TODO
    public void trashRequestsForAuxiliaryNurses() { // Trash 1 to all requests for auxiliary nurses

        Random random = new Random();
        int numberOfTotalRequests = auxiliaryRequests.size();
        int requestsToTrash = random.nextInt(numberOfTotalRequests - 1 + 1) + 1;

        for (int i = 0; i < requestsToTrash; i++) {

        }

    }

    // TODO
    public void virusOutbreak() {

    }

    // TODO
    public void hospitalReports() {

    }

    // ! Medic menu
    protected void listPacientsInHospitalQueue() {
        // * Also used in the administrator menu

        for (Person pacient : pacientsQueue) {
            System.out.println(pacient.getID() + ": " + pacient.getName() + ".");
        }

    }

    protected void listPacientsAwaitingDischarge() throws IDNotFoundException {

        int medicID;
        System.out.println("ID do médico: ");
        medicID = scannerObject.nextInt();

        boolean medicExists = false;

        for (Medic tempMedic : medics) {

            if (tempMedic.getID() == medicID) {
                medicExists = true;

                for (Person pacient : tempMedic.getPacientsAwaitingDischarge()) {
                    System.out.println(pacient.getID() + " - " + pacient.getName());
                }

            }

        }

        if (!medicExists) {
            throw new IDNotFoundException("Não existe um médico com o ID introduzido.");
        }

    }

    // TODO
    protected void pacientDiagnostic() throws NoPacientsToDiagnoseException {

        if (pacientsQueue.size() != 0) {
            Person currentPacient = pacientsQueue.poll();
        }

        else {
            throw new NoPacientsToDiagnoseException("Não há pacientes por diagnosticar.");
        }

    }

    // TODO
    protected void dischargePacient() {
    }

    // TODO
    protected void requisitAuxiliaryNurses() {
        System.out.println("ID do médico: ");
        int medicID;
        medicID = Integer.parseInt(scannerObject.next());
        boolean medicExists = false;

        for (Medic tempMedic : medics) {
            if (medicID == tempMedic.getID()) {
                medicExists = true;
            }
        }

        if (!medicExists) {
            System.out.println("Não existe nenhum médico com o ID inserido.");
            return;
        }

        System.out.println("ID do chefe enfermeiro: ");
        int chiefNurseID;
        chiefNurseID = Integer.parseInt(scannerObject.next());
        boolean chiefNurseExists = false;

        // Check if the chief nurse exists
        for (SpecialistNurse tempChiefNurse : chiefNurses) {
            if (tempChiefNurse.getID() == chiefNurseID) {
                chiefNurseExists = true;
            }
        }

        if (!chiefNurseExists) {
            System.out.println("Não existe nenhum enfermeiro chefe com o ID inserido.");
            return;
        }

        System.out.println("Quantos enfermeiros auxiliares necessita: ");
        int auxiliaryNursesRequested;
        auxiliaryNursesRequested = Integer.parseInt(scannerObject.next());

        if (auxiliaryNursesRequested > auxiliaryNurses.size()) {
            // TODO: Exception
        }
    }

    // ! Nurse menu

    protected void listMedicNurses() throws IDNotFoundException {

        int medicID;
        System.out.println("Nº do médico: ");
        medicID = Integer.parseInt(scannerObject.nextLine());

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

        // If medic doesn't exist, throws an exception
        if (!medicExists) {
            throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
        }

    }

    protected void listPacientsWaitingForCure() throws IDNotFoundException {

        System.out.println("Insira o ID do enfermeiro: ");
        int nurseID = Integer.parseInt(scannerObject.next());

        Nurse nurse = null;

        // TODO: Improve this code so if the nurse is found in a list, it isn't searched
        // in the others.
        for (Nurse tempAuxiliaryNurse : auxiliaryNurses) {
            if (tempAuxiliaryNurse.getID() == nurseID) {
                nurse = tempAuxiliaryNurse;
                break;
            }
        }
        for (Nurse tempSpecialistNurse : specialistNurses) {
            if (tempSpecialistNurse.getID() == nurseID) {
                nurse = tempSpecialistNurse;
                break;
            }
        }
        for (Nurse tempChiefNurse : chiefNurses) {
            if (tempChiefNurse.getID() == nurseID) {
                nurse = tempChiefNurse;
                break;
            }
        }

        // If the nurse doesn't exist, throw an exception
        if (nurse == null) {
            throw new IDNotFoundException("Não existe nenhum enfermeiro com o ID inserido.");
        }

        // If the nurse exists, list pacients waiting for cure
        else {

            Set<Person> pacientsWaitingCure = nurse.getSchedule().keySet();

            for (Person pacient : pacientsWaitingCure) {

                System.out.println(pacient.getID() + ": " + pacient.getName());

            }
        }

    }

    // TODO: Only the chief nurse can do this
    protected void attributeSpecialistNurseToMedic() throws IDNotFoundException {

        // ! Asks the user for the Specialist Nurse to attribute, and checks if it
        // exists
        int specialistNurseID;

        System.out.println("ID do enfermeiro especialista a atribuir: ");
        specialistNurseID = Integer.parseInt(scannerObject.next());

        SpecialistNurse specialistNurse = null;

        // Check if a specialist nurse with the ID exists
        for (SpecialistNurse tempSpecialistNurse : specialistNurses) {

            if (tempSpecialistNurse.getID() == specialistNurseID) {
                specialistNurse = tempSpecialistNurse;
                break;
            }

        }

        // Specialist nurse doesn't exist
        if (specialistNurse == null) {
            System.out.println("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        }

        else {

            // ! If the Specialist nurse exists, asks the user for Medic and checks if it
            // exists

            int medicID;

            System.out.println("ID do médico: ");
            medicID = Integer.parseInt(scannerObject.next());

            Medic medic = null;

            // Check if a medic with the ID exists
            for (Medic tempMedic : medics) {

                if (tempMedic.getID() == medicID) {
                    medic = tempMedic;
                    break;
                }

            }

            // If the medic doesn't exist, throws an exception
            if (medic == null) {
                throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
            }

            // If the medic exists, attribute the specialist to the found medic
            else {
                medic.attributeSpecialistNurse(specialistNurse);
            }

        }
    }

    protected void applyCureToPacient() throws IDNotFoundException {
        System.out.println("Insira o ID do enfermeiro: ");
        int nurseID = Integer.parseInt(scannerObject.next());

        Nurse nurse = null;

        for (Nurse tempAuxiliaryNurse : auxiliaryNurses) {
            if (tempAuxiliaryNurse.getID() == nurseID) {
                nurse = tempAuxiliaryNurse;
                break;
            }
        }
        for (Nurse tempSpecialistNurse : specialistNurses) {
            if (tempSpecialistNurse.getID() == nurseID) {
                nurse = tempSpecialistNurse;
                break;
            }
        }
        for (Nurse tempChiefNurse : chiefNurses) {
            if (tempChiefNurse.getID() == nurseID) {
                nurse = tempChiefNurse;
                break;
            }
        }

        // If the nurse doesn't exist, throw an exception
        if (nurse == null) {
            throw new IDNotFoundException("Não existe nenhum enfermeiro com o ID inserido.");
        }

        // If the nurse exists, list one pacient waiting for cure
        else {

            Set<Person> pacientsWaitingCure = nurse.getSchedule().keySet();
            for (int i = 0; i<1; i++){
                System.out.println(pacientsWaitingCure.iterator().next());
                pacientsWaitingCure.remove(i);
            }

        }

    }
}

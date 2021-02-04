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
import Hospital_Database.Exceptions.NoMedicsExistException;
import Hospital_Database.Exceptions.NoNursesExistException;
import Hospital_Database.Exceptions.NoPacientsAwaitingDischargeException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.Exceptions.NotEnoughAuxiliaryNursesException;
import Hospital_Database.Exceptions.NotEnoughCareerYearsException;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Nurse;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;

public class Hospital {

    // ! Class-based singleton implementation
    private static Hospital INSTANCE;
    private Scanner scanner = new Scanner(System.in);

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

    // Done
    public void addMedic() { // Creates and adds new medic to the hospital

        ClearConsole.clearConsole();

        // Input medic name
        System.out.println("Nome do médico: ");
        String name;
        name = scanner.next();

        // Input medic birthday year
        System.out.println("Ano de nascimento do médico: ");
        int birthdayYear;
        birthdayYear = scanner.nextInt();

        // Create medic, add medic to the list, and add 1 to the last ID
        Medic newMedic = new Medic(lastIDAttributed + 1, name, birthdayYear);
        medics.add(newMedic);
        lastIDAttributed++;

    }

    // Done
    public void addSpecialistNurse() { // Creates and adds specialist nurse to the hospital

        ClearConsole.clearConsole();

        // Input specialist nurse name
        System.out.println("Nome do enfermeiro especialista: ");
        String name = "";
        name = scanner.nextLine();

        // Input specialist nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro especialista: ");
        int birthdayYear;
        birthdayYear = scanner.nextInt();

        // Create new specialist nurse, add it to the list and add 1 to last ID.
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(lastIDAttributed + 1, name, birthdayYear);
        specialistNurses.add(newSpecialistNurse);
        lastIDAttributed++;

    }

    // Done
    public void addAuxiliaryNurse() { // Creates and adds auxiliary nurse to the hospital

        ClearConsole.clearConsole();

        // Input auxiliary nurse name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        String name = "";
        name = scanner.nextLine();

        // Input auxiliary nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro auxiliar: ");
        int birthdayYear;
        birthdayYear = scanner.nextInt();

        // Create new auxiliary nurse, add it to the list, and add 1 to the last ID.
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(lastIDAttributed + 1, name, birthdayYear);
        auxiliaryNurses.add(newAuxiliaryNurse);
        lastIDAttributed++;

    }

    // Done
    public void addNewPacient() { // Adds new pacient to the hospital

        ClearConsole.clearConsole();

        // Input pacient name
        System.out.println("Insira o nome do paciente: ");
        String name = "";
        name = scanner.nextLine();

        // Input pacient birthday year
        System.out.println("Ano de nascimento do novo paciente: ");
        int birthdayYear = scanner.nextInt();

        // Create pacient, add pacient to list, and add 1 to the last ID generated
        Person newPacient = new Person(lastIDAttributed, name, birthdayYear);
        pacientsQueue.add(newPacient);
        lastIDAttributed++;

    }

    // Done
    public void promoteSpecialistNurseToChief() throws NotEnoughCareerYearsException, IDNotFoundException { // Promotes
                                                                                                            // a
                                                                                                            // specialist
                                                                                                            // nurse to
                                                                                                            // chief

        ClearConsole.clearConsole();

        // Input ID of specialist nurse to be promoted
        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = Integer.parseInt(scanner.next());

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
                    throw new NotEnoughCareerYearsException(
                            "O enfermeiro especialista não tem anos de carreira suficientes.");
                }

                break;
            }

        }

        if (!specialistNurseExists) {
            throw new IDNotFoundException("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        }

    }

    // Done
    public void progressNursesCareerYears() {

        // Progress specialist nurses career years
        for (int i = 0; i < specialistNurses.size(); i++) {
            SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
            tempSpecialistNurse.setCareerYears(tempSpecialistNurse.getCareerYears() + 1);
        }

        // Progress auxiliary nurses career years
        for (int i = 0; i < auxiliaryNurses.size(); i++) {
            AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
            tempAuxiliaryNurse.setCareerYears(tempAuxiliaryNurse.getCareerYears() + 1);
        }

        // Progress chief nurses career years
        for (int i = 0; i < chiefNurses.size(); i++) {
            SpecialistNurse tempChiefNurse = chiefNurses.get(i);
            tempChiefNurse.setCareerYears(tempChiefNurse.getCareerYears() + 1);
        }

        System.out.println("Foi adicionado 1 ano de carreira a todos os enfermeiros existentes.");
        scanner.next();

    }

    public void listNurses() throws NoNursesExistException { // Prints all nurses in the hospital to the console

        ClearConsole.clearConsole();

        // TODO: List what medic the nurse is allocated to

        // If no nurses exist in the hospital, throw an exception
        if ((specialistNurses.size() != 0) && (auxiliaryNurses.size() != 0) && (chiefNurses.size() != 0)) {
            throw new NoNursesExistException("Não existem enfermeiros no hospital.");
        }

        // If there are nurses in the hospital, print them to the console
        else {

            // List specialist nurses
            System.out.println("Enfermeiros especialista\n");
            for (int i = 0; i < specialistNurses.size(); i++) {
                SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
                int nurseCareerYears = tempSpecialistNurse.getCareerYears();
                String nurseName = tempSpecialistNurse.getName();
                int nurseID = tempSpecialistNurse.getID();
                System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
            }

            // List auxiliary nurses
            System.out.println("\nEnfermeiros auxiliares\n");
            for (int i = 0; i < auxiliaryNurses.size(); i++) {
                AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
                int nurseCareerYears = tempAuxiliaryNurse.getCareerYears();
                String nurseName = tempAuxiliaryNurse.getName();
                int nurseID = tempAuxiliaryNurse.getID();
                System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
            }

            // List chief nurses
            System.out.println("\nEnfermeiros chefe\n");
            for (int i = 0; i < chiefNurses.size(); i++) {
                SpecialistNurse tempChiefNurse = chiefNurses.get(i);
                int nurseCareerYears = tempChiefNurse.getCareerYears();
                String nurseName = tempChiefNurse.getName();
                int nurseID = tempChiefNurse.getID();
                System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
            }

        }

        // Waits for user input
        scanner.next();

    }

    // Done
    public void listMedics() throws NoMedicsExistException { // Prints all medic in the hospital to the console

        ClearConsole.clearConsole();

        // If there are no medics in the hospital, throw an exception
        if (medics.size() == 0) {
            throw new NoMedicsExistException("Não existem médicos no hospital.");
        }

        // If there are medics in the hospital, print them to the console
        else {

            // Prints all medics to the console
            for (int i = 0; i < medics.size(); i++) {
                Medic tempMedic = medics.get(i);
                System.out.println("Medic");
                System.out.println(tempMedic.toString() + "\n");
            }
        }

        // Waits for user input
        scanner.next();

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

        ClearConsole.clearConsole();

        // Prints pacients information to console
        for (Person pacient : pacientsQueue) {
            // TODO: Use toString method instead
            System.out.println(pacient.getID() + ": " + pacient.getName() + ".");
        }

        scanner.next();
    }

    protected void listPacientsAwaitingDischarge(Medic medic) throws NoPacientsAwaitingDischargeException { // Lists all
                                                                                                            // pacients
                                                                                                            // waiting
                                                                                                            // for
                                                                                                            // discharge,
                                                                                                            // for a
                                                                                                            // given
                                                                                                            // medic

        // If the medic doesn't have pacients awaiting discharge
        if (medic.getPacientsAwaitingDischarge().size() == 0) {
            throw new NoPacientsAwaitingDischargeException("O médico não tem pacientes a aguardar alta.");
        }

        // If the medic has pacients awaiting discharge
        else {

            // Prints all pacients waiting for discharge
            for (Person pacient : medic.getPacientsAwaitingDischarge()) {

                System.out.println("Pacient\n" + pacient.toString());

            }

        }

        // Waits for user input
        scanner.next();
    }

    // TODO
    protected void pacientDiagnostic(Medic medic) throws NoPacientsToDiagnoseException {

        if (pacientsQueue.size() != 0) {
            Person currentPacient = pacientsQueue.poll();
        }

        else {
            throw new NoPacientsToDiagnoseException("Não há pacientes por diagnosticar.");
        }

    }

    // TODO
    protected void dischargePacient(Medic medic) {
    }

    // TODO
    protected void requestAuxiliaryNurses(Medic medic) throws IDNotFoundException, NotEnoughAuxiliaryNursesException { // Sends a request for
                                                                                                  // auxiliary nurses to
                                                                                                  // a chief nurse

        ClearConsole.clearConsole();

        // Input the chief nurse to send the request to
        System.out.println("ID do chefe enfermeiro: ");
        int chiefNurseID;
        chiefNurseID = Integer.parseInt(scanner.next());
        
        // Check if the chief nurse exists
        SpecialistNurse chiefNurse = null;
        for (SpecialistNurse tempChiefNurse : chiefNurses) {
            if (tempChiefNurse.getID() == chiefNurseID) {
                chiefNurse = tempChiefNurse;
            }
        }

        // If the chief nurse doesn't exist, throw an exception
        if (chiefNurse == null) {
            throw new IDNotFoundException("Não existe nenhum enfermeiro chefe com o ID inserido.");
        }

        // If the chief nurse exists, send the request for auxiliary nurses
        else {
            System.out.println("Quantos enfermeiros auxiliares necessita: ");
            int auxiliaryNursesRequested;
            auxiliaryNursesRequested = Integer.parseInt(scanner.next());


            // If there are not enough auxiliary nurses to complete the request, throw an exception
            if (auxiliaryNursesRequested > auxiliaryNurses.size()) {
                // TODO: Request auxiliary nurses exception behaviour
                throw new NotEnoughAuxiliaryNursesException("Não existem enfermeiros auxiliares suficientes no hospital.");
            }

            // If there are enough auxiliary nurses to complete the request, send the request
            else {
                // TODO: Continue
                chiefNurse.getMedicRequests();
                
            }


        }
    }

    // ! Nurse menu

    protected void listMedicNurses() throws IDNotFoundException { // Lists all nurses associated with a medic

        int medicID;
        System.out.println("Nº do médico: ");
        medicID = Integer.parseInt(scanner.nextLine());

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
        int nurseID = Integer.parseInt(scanner.next());

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
        specialistNurseID = Integer.parseInt(scanner.next());

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
            medicID = Integer.parseInt(scanner.next());

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

        ClearConsole.clearConsole();

        // Input nurse ID
        System.out.println("Insira o ID do enfermeiro a aplicar curativo: ");
        int nurseID = Integer.parseInt(scanner.next());

        Nurse nurse = null;

        // Check if the nurse exists
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
            for (int i = 0; i < 1; i++) {
                System.out.println(pacientsWaitingCure.iterator().next());
                // TODO: Check this out
                pacientsWaitingCure.remove(i);
            }

        }

    }
}

package Hospital_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;
import Hospital_Database.Exceptions.NoMedicsExistException;
import Hospital_Database.Exceptions.NoNursesExistException;
import Hospital_Database.Exceptions.NoPacientsInWaitingQueueException;
import Hospital_Database.Exceptions.NotEnoughCareerYearsException;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Person;
import Hospital_Database.Person.SpecialistNurse;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class Hospital {

    // ! Class-based singleton implementation
    private static Hospital INSTANCE;
    private Scanner scanner = new Scanner(System.in);

    public static Hospital getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Hospital();
        }

        return INSTANCE;
    }

    // ! Instance variables

    private static int lastIDAttributed = 0;
    final private int NECESSARY_YEARS_FOR_PROMOTION = 20;
    private List<Medic> medics;
    private List<AuxiliaryNurse> auxiliaryNurses;
    private List<SpecialistNurse> specialistNurses;
    private List<SpecialistNurse> chiefNurses;
    private Queue<Person> pacientsQueue;
    private HashMap<Medic, Integer> auxiliaryRequests;
    private HashMap<Person, ArrayList<Remedy>> registry;

    // ! Contrustor (private because its a singleton)

    private Hospital() {
        medics = new ArrayList<>();
        auxiliaryNurses = new ArrayList<>();
        specialistNurses = new ArrayList<>();
        chiefNurses = new ArrayList<>();
        pacientsQueue = new LinkedList<>();
        auxiliaryRequests = new HashMap<>();
        registry = new HashMap<>();
    }

    // ! Getters and setters

    public HashMap<Person, ArrayList<Remedy>> getRegistry() {
        return registry;
    }

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

    public HashMap<Medic, Integer> getAuxiliaryRequests() {
        return auxiliaryRequests;
    }

    // ! Populate hospital method
    protected void populate(String hospitalDatabaseFilePath) {
        Files.populateHospital(hospitalDatabaseFilePath, this);
    }

    // ! Administrator menu related methods

    public void addMedic() { // Creates and adds new medic to the hospital

        ClearConsole.clearConsole();

        // Input medic name
        System.out.println("Nome do médico: ");
        String name = scanner.next();

        // Input medic birthday year
        System.out.println("Ano de nascimento do médico: ");
        int birthdayYear = scanner.nextInt();

        // Create medic, add medic to the list, and add 1 to the last ID
        Medic newMedic = new Medic(lastIDAttributed + 1, name, birthdayYear);
        medics.add(newMedic);
        lastIDAttributed++;

    }

    public void addSpecialistNurse() { // Creates and adds specialist nurse to the hospital

        ClearConsole.clearConsole();

        // Input specialist nurse name
        System.out.println("Nome do enfermeiro especialista: ");
        String name = scanner.next();

        // Input specialist nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro especialista: ");
        int birthdayYear = scanner.nextInt();

        // Input specialist nurse career years
        System.out.println("Anos de carreira do enfermeiro especialista: ");
        int careerYears = scanner.nextInt();

        // Create new specialist nurse, add it to the list and add 1 to last ID.
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(lastIDAttributed + 1, name, birthdayYear, careerYears);
        specialistNurses.add(newSpecialistNurse);
        lastIDAttributed++;

    }

    public void addAuxiliaryNurse() { // Creates and adds auxiliary nurse to the hospital

        ClearConsole.clearConsole();

        // Input auxiliary nurse name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        String name = scanner.next();

        // Input auxiliary nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro auxiliar: ");
        int birthdayYear = scanner.nextInt();

        // Input auxiliary nurse career years
        System.out.println("Ano de carreira do enfermeiro auxiliar: ");
        int careerYears = scanner.nextInt();

        // Create new auxiliary nurse, add it to the list, and add 1 to the last ID.
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(lastIDAttributed + 1, name, birthdayYear, careerYears);
        auxiliaryNurses.add(newAuxiliaryNurse);
        lastIDAttributed++;

    }

    public void addNewPacient() { // Adds new pacient to the hospital

        ClearConsole.clearConsole();

        // Input pacient name
        System.out.println("Insira o nome do paciente: ");
        String name = scanner.next();

        // Input pacient birthday year
        System.out.println("Ano de nascimento do novo paciente: ");
        int birthdayYear = scanner.nextInt();

        // Create pacient, add pacient to list, and add 1 to the last ID generated
        Person newPacient = new Person(lastIDAttributed, name, birthdayYear);
        pacientsQueue.add(newPacient);
        lastIDAttributed++;

    }

    public void promoteSpecialistNurseToChief() throws NotEnoughCareerYearsException, IDNotFoundException { // Promotes
                                                                                                            // a
                                                                                                            // specialist
                                                                                                            // nurse to
                                                                                                            // chief

        ClearConsole.clearConsole();

        // Input ID of specialist nurse to be promoted
        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = scanner.nextInt();

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

        ClearConsole.clearConsole();
        System.out.println("Foi adicionado 1 ano de carreira a todos os enfermeiros existentes.");
        scanner.next();

    }

    public void listNurses() throws NoNursesExistException { // Prints all nurses in the hospital to the console

        ClearConsole.clearConsole();

        // If no nurses exist in the hospital, throw an exception
        if ((specialistNurses.size() == 0) && (auxiliaryNurses.size() == 0) && (chiefNurses.size() == 0)) {
            throw new NoNursesExistException("Não existem enfermeiros no hospital.");
        }

        // If there are nurses in the hospital, print them to the console
        else {

            // List auxiliary nurses
            System.out.println("Enfermeiros auxiliares\n");
            for (AuxiliaryNurse tempAuxiliaryNurse : auxiliaryNurses) {
                System.out.println(tempAuxiliaryNurse.toString() + "\n");
            }

            // List specialist nurses
            System.out.println("\n\nEnfermeiros especialista\n");
            for (SpecialistNurse tempSpecialistNurse : specialistNurses) {
                System.out.println(tempSpecialistNurse.toString() + "\n");
            }

            // List chief nurses
            System.out.println("\n\nEnfermeiros chefe\n");
            for (SpecialistNurse tempChiefNurse : chiefNurses) {
                System.out.println(tempChiefNurse.toString() + "\n");
            }

        }

        // Waits for user input
        scanner.next();

    }

    public void listMedics() throws NoMedicsExistException { // Prints all medic in the hospital to the console

        ClearConsole.clearConsole();

        // If there are no medics in the hospital, throw an exception
        if (medics.size() == 0) {
            throw new NoMedicsExistException("Não existem médicos no hospital.");
        }

        // If there are medics in the hospital, print them to the console
        else {

            // Prints all medics to the console
            System.out.println("Médicos no hospital\n");
            for (int i = 0; i < medics.size(); i++) {
                Medic tempMedic = medics.get(i);
                System.out.println(tempMedic.toString() + "\n");
            }
        }

        // Waits for user input
        scanner.next();

    }

    public void listRequestsForAuxiliaryNurses() throws NoMedicRequestsExistException {
        // Lists all requests for auxiliary nurses sent to the hospital, in the case
        // they couldn't be completed by a chief nurse

        ClearConsole.clearConsole();

        // If there are no requests in the hospital, throw an exception
        if (auxiliaryRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existem pedidos de enfermeiros auxiliares no hospital.");
        } else {
            System.out.println(auxiliaryRequests.toString());
        }
        // Wait for user input
        scanner.next();

    }

    public void trashRequestsForAuxiliaryNurses() throws NoMedicRequestsExistException { // Trash 1 to all requests for
                                                                                         // auxiliary nurses

        // If there aren't any requests, throw exception
        if (auxiliaryRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existem pedidos de enfermeiros auxiliares.");
        }

        // If there are requests, trash a random number of them
        else {

            Random random = new Random();
            int numberOfTotalRequests = auxiliaryRequests.size();
            int requestsToTrash = random.nextInt(numberOfTotalRequests - 1 + 1) + 1;

            for (int i = 0; i < requestsToTrash; i++) {
                auxiliaryRequests.remove(auxiliaryRequests.keySet().iterator().next());
            }

            System.out.println("Foram removidos " + requestsToTrash + "pedidos de auxiliares.\n");

            // Waits for user input
            scanner.next();
        }
    }

    // TODO: virusOutbreak
    public void virusOutbreak() {
        // Random random = new Random();
        // int randomNumber1 = random.nextInt(2);
        // int randomNumber2 = random.nextInt(3) + 4;

        // for (SpecialistNurse tempSpecialistNurse : chiefNurses) {
        // for (int i = 0; i < tempSpecialistNurse.size(); i++){
        // if (i == randomNumber 1 or i == randomNumber2){

        // }
        // }
        // tempSpecialistNurse[randomNumber1];
        // }
        // for (AuxiliaryNurse tempAuxiliaryNurse : auxiliaryNurses) {

        // }
        // for (Medic tempMedic : medics) {

        // }
    }

    // TODO: HospitalReports
    public void hospitalReports() {

    }

    public void listPacientsInHospitalQueue() throws NoPacientsInWaitingQueueException {

        ClearConsole.clearConsole();

        // If there aren't any pacients in the hospital waiting queue, throw exception
        if (pacientsQueue.size() == 0) {
            throw new NoPacientsInWaitingQueueException("Não existem pacientes na lista de espera.");
        }

        // If there are pacients in the hospital waiting queue, print them to the
        // console
        else {

            // Prints pacients information to console
            System.out.println("Pacientes na lista de espera do hospital\n");
            for (Person pacient : pacientsQueue) {
                System.out.println(pacient.toString() + "\n");
            }

        }

        // Waits for user input
        AwaitsUserInput.awaitsUserInput();

    }
}

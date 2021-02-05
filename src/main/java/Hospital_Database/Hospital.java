package Hospital_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;
import Hospital_Database.Exceptions.NoMedicsExistException;
import Hospital_Database.Exceptions.NoNursesExistException;
import Hospital_Database.Exceptions.NoPacientsInWaitingQueueException;
import Hospital_Database.Exceptions.NotEnoughAuxiliaryNursesException;
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
        String name = Menu.scanner.next();

        // Input medic birthday year
        System.out.println("Ano de nascimento do médico: ");
        int birthdayYear = Menu.scanner.nextInt();

        // Create medic, add medic to the list, and add 1 to the last ID
        Medic newMedic = new Medic(lastIDAttributed + 1, name, birthdayYear);
        medics.add(newMedic);
        lastIDAttributed++;

    }

    public void addSpecialistNurse() { // Creates and adds specialist nurse to the hospital

        ClearConsole.clearConsole();

        // Input specialist nurse name
        System.out.println("Nome do enfermeiro especialista: ");
        String name = Menu.scanner.next();

        // Input specialist nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro especialista: ");
        int birthdayYear = Menu.scanner.nextInt();

        // Input specialist nurse career years
        System.out.println("Anos de carreira do enfermeiro especialista: ");
        int careerYears = Menu.scanner.nextInt();

        // Create new specialist nurse, add it to the list and add 1 to last ID.
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(lastIDAttributed + 1, name, birthdayYear, careerYears);
        specialistNurses.add(newSpecialistNurse);
        lastIDAttributed++;

    }

    public void addAuxiliaryNurse() { // Creates and adds auxiliary nurse to the hospital

        ClearConsole.clearConsole();

        // Input auxiliary nurse name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        String name = Menu.scanner.next();

        // Input auxiliary nurse birthday year
        System.out.println("Ano de nascimento do enfermeiro auxiliar: ");
        int birthdayYear = Menu.scanner.nextInt();

        // Input auxiliary nurse career years
        System.out.println("Ano de carreira do enfermeiro auxiliar: ");
        int careerYears = Menu.scanner.nextInt();

        // Create new auxiliary nurse, add it to the list, and add 1 to the last ID.
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(lastIDAttributed + 1, name, birthdayYear, careerYears);
        auxiliaryNurses.add(newAuxiliaryNurse);
        lastIDAttributed++;

    }

    public void addNewPacient() { // Adds new pacient to the hospital

        ClearConsole.clearConsole();

        // Input pacient name
        System.out.println("Insira o nome do paciente: ");
        String name = Menu.scanner.next();

        // Input pacient birthday year
        System.out.println("Ano de nascimento do novo paciente: ");
        int birthdayYear = Menu.scanner.nextInt();

        // Create pacient, add pacient to list, and add 1 to the last ID generated
        Person newPacient = new Person(lastIDAttributed, name, birthdayYear);
        pacientsQueue.add(newPacient);
        lastIDAttributed++;

    }

    public void promoteSpecialistNurseToChief() throws NotEnoughCareerYearsException, IDNotFoundException {
        // Promotes a specialist nurse to chief

        ClearConsole.clearConsole();

        // Input ID of specialist nurse to be promoted
        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = Menu.scanner.nextInt();

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
        // Add 1 to all nurse career years

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
        Menu.scanner.next();

    }

    public void listNurses() throws NoNursesExistException { // Prints all nurses in the hospital to the console

        ClearConsole.clearConsole();

        // If no nurses exist in the hospital, throw an exception
        if ((specialistNurses.size() == 0) && (auxiliaryNurses.size() == 0) && (chiefNurses.size() == 0)) {
            throw new NoNursesExistException("Não existem enfermeiros no hospital.");
        } // If there are nurses in the hospital, print them to the console
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
        Menu.scanner.next();

    }

    public void listMedics() throws NoMedicsExistException { // Prints all medic in the hospital to the console

        ClearConsole.clearConsole();

        // If there are no medics in the hospital, throw an exception
        if (medics.size() == 0) {
            throw new NoMedicsExistException("Não existem médicos no hospital.");
        } // If there are medics in the hospital, print them to the console
        else {

            // Prints all medics to the console
            System.out.println("Médicos no hospital\n");
            for (int i = 0; i < medics.size(); i++) {
                Medic tempMedic = medics.get(i);
                System.out.println(tempMedic.toString() + "\n");
            }
        }

        // Waits for user input
        Menu.scanner.next();

    }

    public void listRequestsForAuxiliaryNurses() throws NoMedicRequestsExistException {
        // Lists all requests for auxiliary nurses sent to the hospital, in the case
        // they couldn't be completed by a chief nurse

        ClearConsole.clearConsole();

        // If there are no requests in the hospital, throw an exception
        if (auxiliaryRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existem pedidos de enfermeiros auxiliares no hospital.");
        } else {

            ArrayList<Medic> medics = new ArrayList<>(auxiliaryRequests.keySet());
            System.out.println("Pedidos de enfermeiros auxiliares\n");

            for (Medic medic : medics) {
                System.out.println(medic.toString() + "\nNúmero de pedidos: "
                        + String.valueOf(auxiliaryRequests.get(medic)) + "\n");
            }

        }
        // Wait for user input
        Menu.scanner.next();

    }

    public void trashRequestsForAuxiliaryNurses() throws NoMedicRequestsExistException {
        // Trash a random amount of requests for auxiliary nurses made to the hospital

        // If there aren't any requests, throw exception
        if (auxiliaryRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existem pedidos de enfermeiros auxiliares.");
        } // If there are requests in the hospital, trash a random number of them
        else {

            Random random = new Random();

            ArrayList<Medic> medics = new ArrayList<>(auxiliaryRequests.keySet());

            int requestsToTrash = random.nextInt(auxiliaryRequests.size() - 1 + 1) + 1;

            for (int i = 0; i < requestsToTrash; i++) {

                auxiliaryRequests.remove(medics.get(i));

            }

            ClearConsole.clearConsole();
            System.out.println("Foram removidos " + requestsToTrash + " pedidos de auxiliares.\n");

            // Waits for user input
            Menu.scanner.next();
        }
    }

    public void virusOutbreak() {
        // Infects random amount of staff

        Random random = new Random();
        int probabilityOfInfection;

        int peopleInfected = 0;

        // Infects medics
        for (Medic medic : medics) {
            probabilityOfInfection = random.nextInt(10 - 1 + 1) + 1;

            if (probabilityOfInfection == 1) {
                medic.infect();
                pacientsQueue.add(medic);
                medics.remove(medic);
                peopleInfected++;
            }

        }

        // Infects auxiliary nurses
        for (AuxiliaryNurse auxiliaryNurse : auxiliaryNurses) {
            probabilityOfInfection = random.nextInt(10 - 1 + 1) + 1;

            if (probabilityOfInfection == 1) {
                auxiliaryNurse.infect();
                pacientsQueue.add(auxiliaryNurse);
                auxiliaryNurses.remove(auxiliaryNurse);
                peopleInfected++;

            }

        }

        for (SpecialistNurse specialistNurse : specialistNurses) {
            probabilityOfInfection = random.nextInt(10 - 1 + 1) + 1;

            if (probabilityOfInfection == 1) {
                specialistNurse.infect();
                pacientsQueue.add(specialistNurse);
                specialistNurses.remove(specialistNurse);
                peopleInfected++;

            }

        }

        for (SpecialistNurse chiefNurse : chiefNurses) {
            probabilityOfInfection = random.nextInt(10 - 1 + 1) + 1;

            if (probabilityOfInfection == 1) {
                chiefNurse.infect();
                pacientsQueue.add(chiefNurse);
                chiefNurses.remove(chiefNurse);
                peopleInfected++;

            }

        }

        System.out.println("Foram infetadas " + peopleInfected + " pessoas.");
        AwaitsUserInput.awaitsUserInput();

    }

    public void hospitalReports() {
        // Generates a hospital report, and prints it to the console

        ClearConsole.clearConsole();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("                      **RELATORIO HOSPITALAR**");
        System.out.println("---------------------------------------------------------------------------");

        int numberOfDeaths = 0;
        int numberOfMedicalDischarges = 0;
        int numberOfRemediesApplied = 0;

        ArrayList<Person> pacientsInRegistry = new ArrayList<>(registry.keySet());

        for (Person pacient : pacientsInRegistry) {

            numberOfRemediesApplied += registry.get(pacient).size();

            if (pacient.isDead()) {
                numberOfDeaths++;
            } else {
                numberOfMedicalDischarges++;
            }
        }

        int totalPacients = numberOfDeaths + numberOfMedicalDischarges;
        System.out.println("Total de pacientes recebidos: " + totalPacients + ".\n");
        System.out.println("Altas: " + numberOfMedicalDischarges + ".\n");
        System.out.println("Óbitos: " + numberOfDeaths + ".\n");
        System.out.println("Numero de curativos administrados: " + String.valueOf(numberOfRemediesApplied) + "\n");

        AwaitsUserInput.awaitsUserInput();
    }

    public void listPacientsInHospitalQueue() throws NoPacientsInWaitingQueueException {
        // List all pacients in the hospital queue

        ClearConsole.clearConsole();

        // If there aren't any pacients in the hospital waiting queue, throw exception
        if (pacientsQueue.size() == 0) {
            throw new NoPacientsInWaitingQueueException("Não existem pacientes na lista de espera.");
        } // If there are pacients in the hospital waiting queue, print them to the
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

    public void fulfilMedicAuxiliaryRequest() throws NoMedicRequestsExistException, NotEnoughAuxiliaryNursesException {
        // Fulfils a medics request for auxiliary nurses.

        ClearConsole.clearConsole();

        // Input medic ID to fulfil request
        System.out.println("Introduza o ID do médico que requisitou auxiliares: ");
        int medicID = Menu.scanner.nextInt();

        Queue<Medic> medicsAwaitingRequests = new LinkedList<>(auxiliaryRequests.keySet());

        Medic medic = null;
        for (Medic tempMedic : medicsAwaitingRequests) {
            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }
        }

        // If medic doesn't exist, throw an exception
        if (medic == null) {
            throw new NoMedicRequestsExistException(
                    "Não existe nenhum médico com o ID introduzido à espera de auxiliares.");
        } // If medic exists, fulfils his requests
        else {

            int numberOfFreeAuxiliaries = 0;

            for (AuxiliaryNurse auxiliaryNurse : auxiliaryNurses) {
                if (auxiliaryNurse.getAssociatedMedic() == null) {
                    numberOfFreeAuxiliaries++;
                }
            }

            int numberOfRequestedAuxiliaries = auxiliaryRequests.get(medic);

            // If there aren't enough auxiliares, throw error
            if (numberOfFreeAuxiliaries < numberOfRequestedAuxiliaries) {
                throw new NotEnoughAuxiliaryNursesException("Não existem auxiliares suficientes.");
            } // If there are enough auxiliares, fulfil request
            else {
                int numberOfAuxiliariesAttributed = 0;
                for (AuxiliaryNurse tempAuxiliaryNurse : auxiliaryNurses) {
                    // If the associated nurse doesn't have an associated medic already, attributes
                    // it to the medic
                    if (tempAuxiliaryNurse.getAssociatedMedic() == null) {
                        medic.getAuxiliaryNurses().add(tempAuxiliaryNurse);
                        tempAuxiliaryNurse.setAssociatedMedic(medic);
                        numberOfAuxiliariesAttributed++;

                        if (numberOfAuxiliariesAttributed == numberOfRequestedAuxiliaries) {
                            break;
                        }
                    }
                }

                System.out.println("Auxiliares atribuídos");
                AwaitsUserInput.awaitsUserInput();

            }
        }

    }

}

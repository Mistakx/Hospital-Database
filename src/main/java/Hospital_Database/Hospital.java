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
import Hospital_Database.Exceptions.NoMedicRequestsExistException;
import Hospital_Database.Exceptions.NoMedicsExistException;
import Hospital_Database.Exceptions.NoNursesExistException;
import Hospital_Database.Exceptions.NoPacientsAwaitingDischargeException;
import Hospital_Database.Exceptions.NoPacientsInWaitingQueueException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.Exceptions.NotEnoughAuxiliaryNursesException;
import Hospital_Database.Exceptions.NotEnoughCareerYearsException;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
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

    // ! Populate hospital method
    protected void populate(String hospitalDatabaseFilePath) {
        Files.populateHospital(hospitalDatabaseFilePath, this);
    }

    // ! Administrator menu related methods

    // Done
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

    // Done
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

    // Done
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

    // Done
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

        ClearConsole.clearConsole();
        System.out.println("Foi adicionado 1 ano de carreira a todos os enfermeiros existentes.");
        scanner.next();

    }

    // Done
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

    public void trashRequestsForAuxiliaryNurses() { // Trash 1 to all requests for auxiliary nurses

        Random random = new Random();
        int numberOfTotalRequests = auxiliaryRequests.size();
        int requestsToTrash = random.nextInt(numberOfTotalRequests - 1 + 1) + 1;

        for (int i = 0; i < requestsToTrash; i++) {
            
        }

    }

    // TODO
    public void virusOutbreak() {
        Random random = new Random();
        int randomNumber = random.nextInt(6);

        for (SpecialistNurse tempSpecialistNurse : ChiefNurse){
            
        }
    }

    // TODO
    public void hospitalReports() {

    }

    // ! Medic menu related methods
    // Done
    protected void listPacientsInHospitalQueue() throws NoPacientsInWaitingQueueException {
        // * Also used in the administrator menu

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
        scanner.next();

    }

    // Done
    protected void listPacientsAwaitingDischarge(Medic medic) throws NoPacientsAwaitingDischargeException { // Lists all
                                                                                                            // pacients
                                                                                                            // waiting
                                                                                                            // for
                                                                                                            // discharge,
                                                                                                            // for a
                                                                                                            // given
                                                                                                            // medic

        ClearConsole.clearConsole();

        // If the medic doesn't have pacients awaiting discharge
        if (medic.getPacientsAwaitingDischarge().size() == 0) {
            throw new NoPacientsAwaitingDischargeException("O médico não tem pacientes a aguardar alta.");
        }

        // If the medic has pacients awaiting discharge
        else {

            // Prints all pacients waiting for discharge
            for (Person pacient : medic.getPacientsAwaitingDischarge()) {

                System.out.println("Pacient\n" + pacient.toString() + "\n");

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
    protected void requestAuxiliaryNurses(Medic medic) throws IDNotFoundException, NotEnoughAuxiliaryNursesException { // Sends
                                                                                                                       // a
                                                                                                                       // request
                                                                                                                       // for
        // auxiliary nurses to
        // a chief nurse

        ClearConsole.clearConsole();

        // Input the chief nurse to send the request to
        System.out.println("ID do chefe enfermeiro: ");
        int chiefNurseID = scanner.nextInt();

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
            int auxiliaryNursesRequested = scanner.nextInt();

            // If there are not enough auxiliary nurses to complete the request, throw an
            // exception
            if (auxiliaryNursesRequested > auxiliaryNurses.size()) {
                // TODO: Request auxiliary nurses exception behaviour
                throw new NotEnoughAuxiliaryNursesException(
                        "Não existem enfermeiros auxiliares suficientes no hospital.");
            }

            // If there are enough auxiliary nurses to complete the request, send the
            // request
            else {
                // TODO: Continue
                chiefNurse.getMedicRequests();

            }

        }
    }

    // ! Nurse menu related methods

    // Done
    protected void listMedicNurses() throws IDNotFoundException { // Lists all nurses associated with a medic

        ClearConsole.clearConsole();

        int medicID;
        System.out.println("ID do médico: ");
        medicID = scanner.nextInt();

        // Check if a medic with the ID exists
        Medic medic = null;
        for (Medic tempMedic : medics) {

            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }

        }

        // If medic doesn't exist, throws an exception
        if (medic == null) {
            throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
        }

        // If medic exists, prints it's nurses to the console
        else {
            List<AuxiliaryNurse> medicAuxiliaryNurses = medic.getAuxiliaryNurses();
            List<SpecialistNurse> medicSpecialistNurses = medic.getSpecialistNurses();

            System.out.println("Médico (ID: " + medicID + ").\n");
            System.out.println("Enfermeiros auxiliares\n");

            for (AuxiliaryNurse tempAuxiliaryNurse : medicAuxiliaryNurses) {

                System.out.println(tempAuxiliaryNurse.toString() + "\n");

            }

            System.out.println("Enfermeiros especialistas\n");

            for (SpecialistNurse tempSpecialistNurse : medicSpecialistNurses) {

                System.out.println(tempSpecialistNurse.toString() + "\n");

            }

        }

        // Waits for user input
        scanner.next();

    }

    // Done
    protected void listPacientsWaitingForCure(Nurse nurse) throws IDNotFoundException {
        // Prints the pacients awaiting cure to the console

        ClearConsole.clearConsole();

        Set<Person> pacientsWaitingCure = nurse.getSchedule().keySet();

        System.out.println("Pacientes a aguardar cura");

        for (Person pacient : pacientsWaitingCure) {

            System.out.println(pacient.toString());

        }

        // Awaits for user input
        scanner.next();

    }

    // Done
    protected void attributeSpecialistNurseToMedic(ChiefNurse chiefNurse) throws IDNotFoundException { //
        // Attributes a specialist nurse to a medic. This method can only be called by a
        // chief nurse.

        ClearConsole.clearConsole();

        // ! Asks the user for the specialist nurse to attribute to a medic
        System.out.println("ID do enfermeiro especialista a atribuir: ");
        int specialistNurseID = scanner.nextInt();

        SpecialistNurse specialistNurse = null;

        // Check if a specialist nurse with the ID exists
        for (SpecialistNurse tempSpecialistNurse : specialistNurses) {

            if (tempSpecialistNurse.getID() == specialistNurseID) {
                specialistNurse = tempSpecialistNurse;
                break;
            }

        }

        // If the specialist nurse doesn't exist, throw an exception
        if (specialistNurse == null) {
            throw new IDNotFoundException("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        }

        // ! If the specialist nurse exists, asks the user for Medic and checks if it
        // exists
        else {

            System.out.println("ID do médico: ");
            int medicID = scanner.nextInt();

            // Check if a medic with the ID exists
            Medic medic = null;
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
        int nurseID = scanner.nextInt();

        // Check if the nurse exists
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
            for (int i = 0; i < 1; i++) {
                System.out.println(pacientsWaitingCure.iterator().next());
                // TODO: Check this out
                pacientsWaitingCure.remove(i);
            }

        }

    }
}

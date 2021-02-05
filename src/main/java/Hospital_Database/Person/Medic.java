package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.Scanner;

import Hospital_Database.Hospital;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoPacientsAwaitingDischargeException;
import Hospital_Database.Exceptions.NoPacientsInWaitingQueueException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.Exceptions.NotEnoughAuxiliaryNursesException;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class Medic extends Person {

    // ! Instance variables
    private ArrayList<SpecialistNurse> specialistNurses = new ArrayList<>();
    private ArrayList<AuxiliaryNurse> auxiliaryNurses = new ArrayList<>();
    private ArrayList<Person> pacientsAwaitingDischarge = new ArrayList<>();

    // ! Constructor
    public Medic(int ID, String name, int birthdayYear) {
        super(ID, name, birthdayYear);
    }

    // ! Getters
    public ArrayList<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    public ArrayList<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    public ArrayList<Person> getPacientsAwaitingDischarge() {
        return pacientsAwaitingDischarge;
    }

    // ! Overriden methods
    @Override
    public String toString() {

        // Adds all associated auxilary nurses IDs to a array list
        ArrayList<Integer> auxiliaryNursesIDs = new ArrayList<>();
        for (AuxiliaryNurse tempAuxiliaryNurse : auxiliaryNurses) {
            auxiliaryNursesIDs.add(tempAuxiliaryNurse.getID());
        }

        // Adds all associated specialist nurses IDs to a array list
        ArrayList<Integer> specialistNursesIDs = new ArrayList<>();
        for (SpecialistNurse tempSpecialistNurse : specialistNurses) {
            specialistNursesIDs.add(tempSpecialistNurse.getID());
        }

        return "ID: " + getID() + "\n" + "Name: " + getName() + "\n" + "Auxiliary Nurses: "
                + auxiliaryNursesIDs.toString() + "\n" + "Specialist Nurses: " + specialistNursesIDs.toString();

    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;

        Medic medic = (Medic) object;
        return super.getID() == medic.getID();
    }

    // Done
    public void listPacientsInHospitalQueue(Hospital hospital) throws NoPacientsInWaitingQueueException {
        // * Also used in the administrator menu

        ClearConsole.clearConsole();

        // If there aren't any pacients in the hospital waiting queue, throw exception
        if (hospital.getPacientQueue().size() == 0) {
            throw new NoPacientsInWaitingQueueException("Não existem pacientes na lista de espera.");
        }

        // If there are pacients in the hospital waiting queue, print them to the
        // console
        else {

            // Prints pacients information to console
            System.out.println("Pacientes na lista de espera do hospital\n");
            for (Person pacient : hospital.getPacientQueue()) {
                System.out.println(pacient.toString() + "\n");
            }

        }

        // Waits for user input
        AwaitsUserInput.awaitsUserInput();

    }

    // Done
    public void listPacientsAwaitingDischarge() throws NoPacientsAwaitingDischargeException { // Lists all
        // pacients
        // waiting
        // for
        // discharge,
        // for a
        // given
        // medic

        ClearConsole.clearConsole();

        // If the medic doesn't have pacients awaiting discharge
        if (pacientsAwaitingDischarge.size() == 0) {
            throw new NoPacientsAwaitingDischargeException("O médico não tem pacientes a aguardar alta.");
        }

        // If the medic has pacients awaiting discharge
        else {

            // Prints all pacients waiting for discharge
            for (Person pacient : pacientsAwaitingDischarge) {

                System.out.println("Pacient\n" + pacient.toString() + "\n");

            }

        }

        // Waits for user input
        AwaitsUserInput.awaitsUserInput();
    }

    // TODO
    public void pacientDiagnostic(Hospital hospital) throws NoPacientsToDiagnoseException {
        // Starts the pacient's diagnostic process

        // If there are no pacients in the hospital waiting queue, throw an exception
        if (hospital.getPacientQueue().size() == 0) {
            throw new NoPacientsToDiagnoseException("Não há pacientes por diagnosticar.");

        }

        // If there are pacients in the hospital waiting queue, diagnose the first
        else {
            Person currentPacient = hospital.getPacientQueue().poll();
        }

    }

    // TODO
    public void dischargePacient() {
    }

    // TODO
    public void requestAuxiliaryNurses(Hospital hospital)
            throws IDNotFoundException, NotEnoughAuxiliaryNursesException { // Sends
        // a
        // request
        // for
        // auxiliary nurses to
        // a chief nurse

        ClearConsole.clearConsole();

        // Input the chief nurse to send the request to
        System.out.println("ID do chefe enfermeiro: ");
        Scanner scanner = new Scanner(System.in);
        int chiefNurseID = scanner.nextInt();

        // Check if the chief nurse exists
        SpecialistNurse chiefNurse = null;
        for (SpecialistNurse tempChiefNurse : hospital.getChiefNurses()) {
            if (tempChiefNurse.getID() == chiefNurseID) {
                chiefNurse = tempChiefNurse;
            }
        }

        // If the chief nurse doesn't exist, throw an exception
        if (chiefNurse == null) {
            scanner.close();
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
                scanner.close();
                throw new NotEnoughAuxiliaryNursesException(
                        "Não existem enfermeiros auxiliares suficientes no hospital.");
            }

            // If there are enough auxiliary nurses to complete the request, send the
            // request
            else {
                chiefNurse.getMedicAuxiliaryRequests().put(this, auxiliaryNursesRequested);
                System.out.println("Foram requisitados " + Integer.toString(auxiliaryNursesRequested) + "\n");
                AwaitsUserInput.awaitsUserInput();
            }

        }

        scanner.close();
    }

}
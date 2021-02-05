package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import Hospital_Database.Hospital;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.MaximumCapacityFilled;
import Hospital_Database.Exceptions.NoPacientsAwaitingDischargeException;
import Hospital_Database.Exceptions.NoPacientsInWaitingQueueException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.Exceptions.NoSpecialistNursesAttributedToMedicException;
import Hospital_Database.Exceptions.NotEnoughAuxiliaryNursesException;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class Medic extends Person {

    // ! Instance variables
    private ArrayList<SpecialistNurse> specialistNurses;
    private ArrayList<AuxiliaryNurse> auxiliaryNurses;
    private Queue<Person> pacientsAwaitingDiagnotic;
    private Queue<Person> pacientsAwaitingDischarge;

    // ! Constructor
    public Medic(int ID, String name, int birthdayYear) {
        super(ID, name, birthdayYear);
        specialistNurses = new ArrayList<>();
        auxiliaryNurses = new ArrayList<>();
        pacientsAwaitingDischarge = new LinkedList<>();
        pacientsAwaitingDiagnotic = new LinkedList<>();
    }

    // ! Getters

    public Queue<Person> getPacientsAwaitingDiagnotic() {
        return pacientsAwaitingDiagnotic;
    }

    public ArrayList<AuxiliaryNurse> getAuxiliaryNurses() {
        return auxiliaryNurses;
    }

    public ArrayList<SpecialistNurse> getSpecialistNurses() {
        return specialistNurses;
    }

    public Queue<Person> getPacientsAwaitingDischarge() {
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

    public void pacientDiagnostic(Hospital hospital)
            throws NoPacientsToDiagnoseException, NoSpecialistNursesAttributedToMedicException, MaximumCapacityFilled {
        // Starts the pacient's diagnostic process. The process needs a specialist
        // nurse, but its up to the medic to decide if there is a need to request
        // auxiliary nurses beforehand

        // If there are no pacients in the hospital waiting queue, throw an exception
        if (hospital.getPacientQueue().size() == 0) {
            throw new NoPacientsToDiagnoseException("Não há pacientes por diagnosticar.");
        }

        // If the medic already has 3 pacients associated
        if (pacientsAwaitingDiagnotic.size() == 3) {
            throw new MaximumCapacityFilled("O médico já tem 3 pacientes.");
        }

        // If the medic doesn't have a specialist nurse attributed
        if (specialistNurses.size() == 0) {
            throw new NoSpecialistNursesAttributedToMedicException(
                    "Não tem enfermeiros especialistas atribuídos para começar o diagnóstico.");
        }

        // If there are pacients in the hospital waiting queue, diagnose the first
        else {

            Person currentPacient = pacientsAwaitingDiagnotic.poll();

            // Generate the person symptoms
            Random random = new Random();

            int temperature = 35 + random.nextInt(7);
            double whiteBloodCellLevels = 0.05 + (Math.random() * (0.96));
            boolean gastrointestinalSymptoms = Math.random() < 0.5;

            currentPacient.setTemperature(temperature);
            currentPacient.setWhiteBloodCellLevels(whiteBloodCellLevels);
            currentPacient.setGastrointestinalSymptoms(gastrointestinalSymptoms);

            // Sends the pacient to a specialist nurse, to help with the diagnostic
            specialistNurses.get(0).getPacientsWaitingForDiagnostic().add(currentPacient);

        }

    }

    public void dischargePacient() throws NoPacientsAwaitingDischargeException {
        // Sends the pacient to a specialist nurse, to verify if the pacient still has
        // any symptoms

        // If there are no pacients awaiting discharge, throw an exception
        if (pacientsAwaitingDischarge.size() == 0) {
            throw new NoPacientsAwaitingDischargeException("Não existem pacientes à espera de alta.");
        }

        // If there are pacients awaiting discharge, send the first to a specialist
        // nurse, for a new diagnostic
        else {

            Person currentPacient = pacientsAwaitingDischarge.poll();

            // Generate the person symptoms
            Random random = new Random();

            int temperature = 35 + random.nextInt(7);
            double whiteBloodCellLevels = 0.05 + (Math.random() * (0.96));
            boolean gastrointestinalSymptoms = Math.random() < 0.5;

            currentPacient.setTemperature(temperature);
            currentPacient.setWhiteBloodCellLevels(whiteBloodCellLevels);
            currentPacient.setGastrointestinalSymptoms(gastrointestinalSymptoms);

            // Sends the pacient to a specialist nurse
            specialistNurses.get(0).getPacientsWaitingForDiagnostic().add(currentPacient);
        }

    }

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
            ClearConsole.clearConsole();
            System.out.println("Quantos enfermeiros auxiliares necessita: ");
            int auxiliaryNursesRequested = scanner.nextInt();

            // If there are not enough free auxiliary nurses to complete the request, throw
            // an
            // exception
            int numberOfFreeAuxiliaries = 0;

            // Counts the number of auxiliary nurses with no medics associated
            for (AuxiliaryNurse tempAuxiliaryNurse : hospital.getAuxiliaryNurses()) {
                if (tempAuxiliaryNurse.getAssociatedMedic() != null) {
                    numberOfFreeAuxiliaries++;
                }
            }

            // If there are not enough auxiliary nurses to complete the request, send the
            // request to the hospital
            if (auxiliaryNursesRequested > numberOfFreeAuxiliaries) {
                scanner.close();
                hospital.getAuxiliaryRequests().put(this, auxiliaryNursesRequested);
                throw new NotEnoughAuxiliaryNursesException(
                        "Não existem enfermeiros auxiliares suficientes. O pedido foi enviado para o hospital.");
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
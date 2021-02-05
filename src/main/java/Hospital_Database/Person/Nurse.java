package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import Hospital_Database.Hospital;
import Hospital_Database.Remedy;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoPacientsAwaitingCureException;
import Hospital_Database.Exceptions.NoPacientsToDiagnoseException;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class Nurse extends Person {

    private Medic associatedMedic;
    private HashMap<Person, ArrayList<Remedy>> schedule;
    private int careerYears;
    private Queue<Person> pacientsWaitingForDiagnostic;

    // ! Constructor
    public Nurse(int ID, String name, int birthdayYear, int careerYears) {
        super(ID, name, birthdayYear);
        this.careerYears = careerYears;
        schedule = new HashMap<Person, ArrayList<Remedy>>();
        pacientsWaitingForDiagnostic = new LinkedList<>();

    }

    // ! Getters & Setters
    public int getCareerYears() {
        return careerYears;
    }

    public void setCareerYears(int newCareerYears) {
        careerYears = newCareerYears;
    }

    public HashMap<Person, ArrayList<Remedy>> getSchedule() {
        return schedule;
    }

    public Medic getAssociatedMedic() {
        return associatedMedic;
    }

    public void setAssociatedMedic(Medic newAssociatedMedic) {
        associatedMedic = newAssociatedMedic;
    }

    // ! Getters
    public Queue<Person> getPacientsWaitingForDiagnostic() {
        return pacientsWaitingForDiagnostic;
    }

    // ! Nurse menu related methods

    public void helpsDiagnostic(Hospital hospital) throws NoPacientsToDiagnoseException {

        // If there aren't any pacients waiting for diagnostic, throw an exception
        if (pacientsWaitingForDiagnostic.size() == 0) {
            throw new NoPacientsToDiagnoseException("Não existem paciente à espera de diagnóstico.");
        }

        // If there are pacients waiting for diagnostic, diagnose the first one
        else {
            Person currentPacient = pacientsWaitingForDiagnostic.poll();

            System.out.println(currentPacient + ".");
            System.out.println("Temperatura: " + currentPacient.getTemperature() + ".");
            System.out.println("Nível de glóbulos brancos: " + currentPacient.getWhiteBloodCellLevels() + ".");

            if (currentPacient.hasGastrointestinalSymptoms()) {
                System.out.println("Sintomas gastrointestinais: Sim.");
            } else {
                System.out.println("Sintomas gastrointestinais: Não.");
            }

            // Verify the symptoms and add the pacient to the schedule, to apply the various
            // cures
            ArrayList<Remedy> remediesToApply = new ArrayList<>();
            boolean hasSymptoms = false;

            if (currentPacient.getTemperature() > 37.5) {
                hasSymptoms = true;
                remediesToApply.add(new Remedy("Covid"));
            }

            if (currentPacient.getWhiteBloodCellLevels() < 0.5) {
                hasSymptoms = true;

                remediesToApply.add(new Remedy("HIV"));
            }

            if (currentPacient.hasGastrointestinalSymptoms()) {
                hasSymptoms = true;
                remediesToApply.add(new Remedy("Ebola"));
            }

            // If the pacient doesn't have symptoms, remove it from the hospital
            if (!hasSymptoms) {
                hospital.getRegistry().put(currentPacient, remediesToApply);
                System.out.println("O paciente não tinha sintomas, e foi removido do hospital\n");
                AwaitsUserInput.awaitsUserInput();
            }

            // If the pacient has symptoms, add it to schedule
            if (hasSymptoms) {
                schedule.put(currentPacient, remediesToApply);
                System.out.println("O paciente tem sintomas, e foi adicionado à agenda de curativos.\n");
                AwaitsUserInput.awaitsUserInput();
            }

        }
    }

    public void applyCureToPacient(Hospital hospital) throws NoPacientsAwaitingCureException {
        // Applies cure to the first pacient in the nurses waiting schedule

        ClearConsole.clearConsole();

        // List<Person> pacientsWaitingCure = new ArrayList<>(schedule.keySet());
        Queue<Person> pacientsWaitingCure = new LinkedList<>(schedule.keySet());

        // If there are no pacients waiting for cure, throw an exception
        if (pacientsWaitingCure.size() == 0) {
            throw new NoPacientsAwaitingCureException("Não existe nenhum paciente à espera de cura.\n");
        }

        // If there are pacients waiting for cure, apply cure to the first one
        else {

            Person currentPacient = pacientsWaitingCure.poll();

            // Apply cure to first pacient
            System.out.println("Introduza a data do curativo: ");
            Scanner scanner = new Scanner(System.in);
            String cureDate = scanner.next();

            // Sets the date of the remedy
            for (Remedy remedyToApply : schedule.get(currentPacient)) {
                remedyToApply.setDateApplied(cureDate);
            }

            Random random = new Random();
            int probabilityOfDeath = random.nextInt(10 - 1 + 1) + 1;

            // Pacient died after administring cure, save to hospital history
            if (probabilityOfDeath == 1) {
                currentPacient.setDead(true);

                hospital.getRegistry().put(currentPacient, schedule.get(currentPacient));

                associatedMedic.getPacientsAwaitingDiagnotic().remove(currentPacient);
                pacientsWaitingCure.remove(currentPacient);

                System.out.println("O paciente morreu após serem aplicados os curativos.");
                scanner.next();

            }

            // If the cure is sucessful, send to medic for discharge
            else {
                associatedMedic.getPacientsAwaitingDischarge().add(currentPacient);
                System.out.println("Os curativos foram bem sucedidos, o paciente foi enviado de volta ao médico.");
                scanner.next();
            }

            scanner.close();

        }
    }

    public void listPacientsWaitingForCure() {
        // Prints the pacients awaiting cure to the console

        ClearConsole.clearConsole();

        Set<Person> pacientsWaitingCure = schedule.keySet();

        System.out.println("Pacientes a aguardar cura");

        for (Person pacient : pacientsWaitingCure) {

            System.out.println(pacient.toString());

        }

        // Awaits for user input
        AwaitsUserInput.awaitsUserInput();
    }

    public void listPacientsWaitingForDiagnostic() {
        // Prints the pacients awaiting diagnostic to the console

        ClearConsole.clearConsole();

        System.out.println("Pacientes a aguardar diagnóstico");

        for (Person pacient : pacientsWaitingForDiagnostic) {

            System.out.println(pacient.toString());

        }

        // Awaits for user input
        AwaitsUserInput.awaitsUserInput();
    }

    public void listMedicNurses(Hospital hospital) throws IDNotFoundException { // Lists all nurses associated with a
                                                                                // medic

        ClearConsole.clearConsole();

        Scanner scanner = new Scanner(System.in);
        int medicID;
        System.out.println("ID do médico: ");
        medicID = scanner.nextInt();

        // Check if a medic with the ID exists
        Medic medic = null;
        for (Medic tempMedic : hospital.getMedics()) {

            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }

        }

        // If medic doesn't exist, throws an exception
        if (medic == null) {
            scanner.close();
            throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
        }

        // If medic exists, prints it's nurses to the console
        else {

            ClearConsole.clearConsole();

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
        AwaitsUserInput.awaitsUserInput();

        scanner.close();

    }

    @Override
    public String toString() {

        // If the nurse isn't associated to a medic
        if (associatedMedic == null) {
            return "ID: " + super.getID() + "\n" + "Nome: " + super.getName() + "\n" + "Anos de carreira: "
                    + careerYears;

        }

        // If the nurse is associated to a medic
        else {
            return "ID: " + super.getID() + "\n" + "Nome: " + super.getName() + "\n" + "Anos de carreira: "
                    + careerYears + "Alocado ao médico:\n" + "ID: " + associatedMedic.getID() + "\n" + "Nome: "
                    + associatedMedic.getName();
        }

    }

}
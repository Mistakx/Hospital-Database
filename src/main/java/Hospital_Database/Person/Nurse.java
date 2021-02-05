package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import Hospital_Database.Hospital;
import Hospital_Database.Remedy;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoPacientsAwaitingCureException;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class Nurse extends Person {

    // TODO: Abstract?

    private Medic associatedMedic; // TODO: Associate medic to nurse
    private HashMap<Person, ArrayList<Remedy>> Schedule;
    private int careerYears;

    // ! Constructor
    public Nurse(int ID, String name, int birthdayYear, int careerYears) {
        super(ID, name, birthdayYear);
        this.careerYears = careerYears;
        Schedule = new HashMap<Person, ArrayList<Remedy>>();
    }

    // ! Getters & Setters
    public int getCareerYears() {
        return careerYears;
    }

    public void setCareerYears(int newCareerYears) {
        careerYears = newCareerYears;
    }

    public HashMap<Person, ArrayList<Remedy>> getSchedule() {
        return Schedule;
    }

    // ! Nurse menu related methods

    // TODO
    public void helpsPacientDiagnostic(Person currentPacient) {

        Random random = new Random();

        int temperature = 35 + random.nextInt(7);
        double whiteBloodCellLevels = 0.05 + (Math.random() * (0.96));
        boolean gastrointestinalSymptoms = Math.random() < 0.5;

        System.out.println(currentPacient + ".");
        System.out.println("Temperatura: " + temperature + ".");
        System.out.println("Nível de glóbulos brancos: " + whiteBloodCellLevels + ".");

        if (gastrointestinalSymptoms) {
            System.out.println("Sintomas gastrointestinais: Sim.");
        } else {
            System.out.println("Sintomas gastrointestinais: Não.");
        }

        // ! Verify the symptons and add the pacient
        ArrayList<Remedy> remediesToApply = new ArrayList<>();

        if (temperature > 37.5) {
            remediesToApply.add(new Remedy("Covid"));
        }

        if (whiteBloodCellLevels < 0.5) {
            remediesToApply.add(new Remedy("HIV"));
        }

        if (gastrointestinalSymptoms) {
            remediesToApply.add(new Remedy("Ebola"));
        }

        // TODO: If no diseases found in pacient
        Schedule.put(currentPacient, remediesToApply);

    }

    // TODO
    public void applyCureToPacient() throws NoPacientsAwaitingCureException {
        // Applies cure to pacient in the nurses waiting schedule

        ClearConsole.clearConsole();

        Set<Person> pacientsWaitingCure = Schedule.keySet();

        if (pacientsWaitingCure.size() == 0) {
            throw new NoPacientsAwaitingCureException("Não existe nenhum paciente à espera de cura.\n");

        }
        for (int i = 0; i < 1; i++) {
            System.out.println(pacientsWaitingCure.iterator().next());
            // TODO: Check this out
            pacientsWaitingCure.remove(i);
        }

    }

    // Done
    public void listPacientsWaitingForCure() {
        // Prints the pacients awaiting cure to the console

        ClearConsole.clearConsole();

        Set<Person> pacientsWaitingCure = Schedule.keySet();

        System.out.println("Pacientes a aguardar cura");

        for (Person pacient : pacientsWaitingCure) {

            System.out.println(pacient.toString());

        }

        // Awaits for user input
        AwaitsUserInput.awaitsUserInput();
    }

    // Done
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
        return "ID: " + super.getID() + "\n" + "Nome: " + super.getName() + "\n" + "Anos de carreira: " + careerYears
                + "Alocado ao médico:\n" + "ID: " + associatedMedic.getID() + "\n" + "Nome: "
                + associatedMedic.getName();
    }

}
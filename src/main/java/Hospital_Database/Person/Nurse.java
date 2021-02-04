package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Hospital_Database.Remedy;

public class Nurse extends Person {

    // TODO: Abstract?

    private Medic associatedMedic; // TODO: Associate medic to nurse
    private HashMap<Person, ArrayList<Remedy>> Schedule = new HashMap<Person, ArrayList<Remedy>>();
    private int careerYears = 0;

    public Nurse(int ID, String name, int birthdayYear, int careerYears) {
        super(ID, name, birthdayYear);
        this.careerYears = careerYears;
    }

    public int getCareerYears() {
        return careerYears;
    }

    public void setCareerYears(int newCareerYears) {
        careerYears = newCareerYears;
    }

    public HashMap<Person, ArrayList<Remedy>> getSchedule() {
        return Schedule;
    }

    protected void helpsPacientDiagnostic(Person currentPacient) {

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

    @Override
    public String toString() {
        return "ID: " + super.getID() + "\n" + "Nome: " + super.getName() + "Career Years: " + careerYears;
    }
}
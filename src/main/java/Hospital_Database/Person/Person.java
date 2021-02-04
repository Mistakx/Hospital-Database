/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database.Person;

/**
 *
 * @author mistakx
 */
public class Person implements Infectable {
    private int ID;
    private String name;
    private int birthdayYear;
    private double temperature;
    private double whiteBloodCellLevels;
    private boolean gastrointestinalSymptoms;

    public Person(int ID, String name, int birthdayYear, double temperature, double whiteBloodCellLevels,
            boolean gastrointestinalSymptoms) {
        this.ID = ID;
        this.name = name;
        this.birthdayYear = birthdayYear;
        this.setTemperature(temperature);
        this.setWhiteBloodCellLevels(whiteBloodCellLevels);
        this.setGastrointestinalSymptoms(gastrointestinalSymptoms);

    }

    public Person(int ID, String name, int birthdayYear) {
        this.ID = ID;
        this.name = name;
        this.birthdayYear = birthdayYear;

    }

    public boolean hasGastrointestinalSymptoms() {
        return gastrointestinalSymptoms;
    }

    public void setGastrointestinalSymptoms(boolean gastrointestinalSymptoms) {
        this.gastrointestinalSymptoms = gastrointestinalSymptoms;
    }

    public double getWhiteBloodCellLevels() {
        return whiteBloodCellLevels;
    }

    public void setWhiteBloodCellLevels(double whiteBloodCellLevels) {
        this.whiteBloodCellLevels = whiteBloodCellLevels;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    public int getID() {
        return ID;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        // Translates the boolean to portuguese, so it can be printed in the console
        String gastrointestinalSymptomsTranslated;
        if (gastrointestinalSymptoms) {
            gastrointestinalSymptomsTranslated = "Verdadeiro";
        } else {
            gastrointestinalSymptomsTranslated = "Falso";
        }

        return "ID: " + ID + "\n" + "Nome: " + name + "\n" + "Ano de nascimento: " + birthdayYear + "\n"
                + "Temperatura: " + temperature + "\n" + "Nível de glóbulos brancos: " + whiteBloodCellLevels + "\n"
                + "Sintomas gastrointestinais: " + gastrointestinalSymptomsTranslated;

    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object == null)
            return false;

        if (this.getClass() != object.getClass())
            return false;

        Person person = (Person) object;
        return ID == person.getID();
    }

}

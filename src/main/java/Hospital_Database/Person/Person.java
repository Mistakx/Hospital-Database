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
public class Person {
    private int ID;
    private String name;
    private int birthdayYear;
    private int temperature;
    private int whiteBloodCellLevels;
    private boolean gastrointestinalSymptoms;

    public Person(int ID, String name, int birthdayYear, int temperature, int whiteBloodCellLevels,
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

    public int getWhiteBloodCellLevels() {
        return whiteBloodCellLevels;
    }

    public void setWhiteBloodCellLevels(int whiteBloodCellLevels) {
        this.whiteBloodCellLevels = whiteBloodCellLevels;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
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

}

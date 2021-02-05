/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database.Person;

import java.util.Random;

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
    private boolean dead;

    public Person(int ID, String name, int birthdayYear) {
        this.ID = ID;
        this.name = name;
        this.birthdayYear = birthdayYear;
        this.dead = false;

    }

    // ! Getters and setters
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
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

    // ! Overriding methods

    public void infect() {
        // Generate the person symptoms
        Random random = new Random();

        temperature = 35 + random.nextInt(7);
        whiteBloodCellLevels = 0.05 + (Math.random() * (0.96));
        gastrointestinalSymptoms = Math.random() < 0.5;

    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Nome: " + name + "\n" + "Ano de nascimento: " + birthdayYear + ".";
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

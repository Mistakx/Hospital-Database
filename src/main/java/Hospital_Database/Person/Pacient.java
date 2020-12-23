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
public class Pacient extends Person {
    private String disease;
    private int birthdayYear;


    public Pacient(int ID, String disease, int birthdayYear) {
        setID(ID); // TODO: Bernardo: Setter or protected on the superclass?
        this.disease = disease;
        this.birthdayYear = birthdayYear;
    }

    // Disease
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    // Birthday year
    protected int getBirthdayYear() {
        return birthdayYear;
    }

    protected void setBirthdayYear(int newBirthdayYear) {
        birthdayYear = newBirthdayYear;
    }

}

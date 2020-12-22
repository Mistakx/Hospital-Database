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

    private int birthdayYear;
    
    public Pacient(int ID, int birthdayYear){setID(ID); this.birthdayYear = birthdayYear;}
    
    protected int getBirthdayYear(){return birthdayYear;}

    protected void setBirthdayYear(int newBirthdayYear){birthdayYear = newBirthdayYear;}
    
    
    
    
    
    
    
}

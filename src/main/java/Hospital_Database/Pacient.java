/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

/**
 *
 * @author mistakx
 */
public class Pacient {

    private int id; 
    private int birthdayYear;
    
    public Pacient(int id, int birthdayYear){this.id = id; this.birthdayYear = birthdayYear;}
    
    protected int getID(){return id;}
    protected void setID(int newID){id = newID;}
    
    protected int getBirthdayYear(){return birthdayYear;}
    protected void setBirthdayYear(int newBirthdayYear){birthdayYear = newBirthdayYear;}
    
    
    
    
    
    
    
}

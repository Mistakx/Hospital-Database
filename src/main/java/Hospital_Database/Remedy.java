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
public class Remedy {
    private String name;
    private String dateApplied;
    
    public Remedy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }
    public String getDateApplied() {
    return dateApplied;
}
}
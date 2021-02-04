/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database.Person;

import java.util.HashMap;

import Hospital_Database.Exceptions.NoMedicRequestsExistException;

/**
 *
 * @author mistakx
 */
public interface ChiefNurse {

    public abstract HashMap<Medic, Integer> getMedicRequests();

    public abstract void listMedicRequests() throws NoMedicRequestsExistException;

    public abstract void fulfilMedicRequest();

}

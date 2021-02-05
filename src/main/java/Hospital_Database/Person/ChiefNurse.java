/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database.Person;

import java.util.HashMap;

import Hospital_Database.Hospital;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;

/**
 *
 * @author mistakx
 */
public interface ChiefNurse {

    public abstract void attributeSpecialistNurseToMedic(Hospital hospital) throws IDNotFoundException;

    public abstract HashMap<Medic, Integer> getMedicAuxiliaryRequests();

    public abstract void listMedicAuxiliaryRequests() throws NoMedicRequestsExistException;

    public abstract void fulfilMedicAuxiliaryRequest(Hospital hospital) throws IDNotFoundException;

}

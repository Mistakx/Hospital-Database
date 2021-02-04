/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database.Exceptions;

/**
 *
 * @author mistakx
 */
public class NoPacientsAwaitingDischargeException extends Exception{
    
        /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsAwaitingDischargeException(String message) {
        super(message);
    }
    
}

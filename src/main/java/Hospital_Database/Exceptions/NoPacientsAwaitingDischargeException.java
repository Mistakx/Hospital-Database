package Hospital_Database.Exceptions;

public class NoPacientsAwaitingDischargeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsAwaitingDischargeException(String message) {
        super(message);
    }

}

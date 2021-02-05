package Hospital_Database.Exceptions;

public class NoPacientsAwaitingCureException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsAwaitingCureException(String message) {
        super(message);
    }
}

package Hospital_Database.Exceptions;

public class NoPacientsWaitingForDiagnosticException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsWaitingForDiagnosticException(String message) {
        super(message);
    }

}

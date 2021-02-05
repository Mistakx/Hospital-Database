package Hospital_Database.Exceptions;

public class NoPacientsToDiagnoseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsToDiagnoseException(String message) {
        super(message);
    }

}

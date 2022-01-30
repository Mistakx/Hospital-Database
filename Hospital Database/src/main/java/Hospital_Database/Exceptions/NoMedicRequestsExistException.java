package Hospital_Database.Exceptions;

public class NoMedicRequestsExistException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoMedicRequestsExistException(String message) {
        super(message);
    }

}

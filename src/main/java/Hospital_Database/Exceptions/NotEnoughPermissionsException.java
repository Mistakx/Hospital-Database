package Hospital_Database.Exceptions;

public class NotEnoughPermissionsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotEnoughPermissionsException(String message) {
        super(message);
    }

}

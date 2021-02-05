package Hospital_Database.Exceptions;

public class NurseAlreadyAttributedToMedicException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NurseAlreadyAttributedToMedicException(String message) {
        super(message);
    }
}

package Hospital_Database.Exceptions;

public class NoPacientsInWaitingQueueException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoPacientsInWaitingQueueException(String message) {
        super(message);
    }
    
}

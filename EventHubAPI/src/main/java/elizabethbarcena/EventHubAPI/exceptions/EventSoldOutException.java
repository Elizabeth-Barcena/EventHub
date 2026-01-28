package elizabethbarcena.EventHubAPI.exceptions;

public class EventSoldOutException extends RuntimeException{
    public EventSoldOutException(Long id) {
        super("Event not found with id: " + id);
    }
}

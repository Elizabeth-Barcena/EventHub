package elizabethbarcena.EventHubAPI.exceptions;

public class EventInvalidException extends RuntimeException {
    public EventInvalidException(Long id) {
        super("Event invalid with id: " + id);
    }
}

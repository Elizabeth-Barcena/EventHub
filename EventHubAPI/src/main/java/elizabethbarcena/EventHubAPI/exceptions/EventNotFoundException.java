package elizabethbarcena.EventHubAPI.exceptions;
public class EventNotFoundException extends BusinessException {

    public EventNotFoundException(Long id) {
        super(ErrorCode.EVENT_NOT_FOUND,"Event not found with id: " + id);
    }
}
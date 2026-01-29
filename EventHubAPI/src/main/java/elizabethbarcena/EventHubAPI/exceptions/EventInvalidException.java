package elizabethbarcena.EventHubAPI.exceptions;

public class EventInvalidException extends BusinessException {
    public EventInvalidException(Long id) {
        super(ErrorCode.EVENT_INVALID, "Event invalid with id: " + id);
    }
}

package elizabethbarcena.EventHubAPI.exceptions;

public class EventHasTicketsException extends BusinessException {

    public EventHasTicketsException(Long eventId) {
        super(
                ErrorCode.EVENT_INVALID,
                "Event with id " + eventId + " cannot be deleted because it has sold tickets"
        );
    }
}

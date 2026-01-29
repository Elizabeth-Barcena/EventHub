package elizabethbarcena.EventHubAPI.exceptions;

public class EventSoldOutException extends BusinessException {

    public EventSoldOutException(Long eventId) {
        super(
                ErrorCode.EVENT_SOLD_OUT,
                "Event with id " + eventId + " is sold out"
        );
    }
}

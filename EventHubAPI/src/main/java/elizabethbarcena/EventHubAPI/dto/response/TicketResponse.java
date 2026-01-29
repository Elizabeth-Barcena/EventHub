package elizabethbarcena.EventHubAPI.dto.response;

import java.time.LocalDateTime;

public class TicketResponse {

    private Long id;
    private Long eventId;
    private String eventName;
    private ParticipantResponse participant;
    private LocalDateTime purchaseDate;

    public TicketResponse(
            Long id,
            Long eventId,
            String eventName,
            ParticipantResponse participant,
            LocalDateTime purchaseDate
    ) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.participant = participant;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public ParticipantResponse getParticipant() {
        return participant;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}

package elizabethbarcena.EventHubAPI.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TicketRequest {

    @NotNull(message = "Event id is required")
    private Long eventId;

    @Valid
    @NotNull(message = "Participant information is required")
    private ParticipantRequest participant;

    public Long getEventId() {
        return eventId;
    }

    public ParticipantRequest getParticipant() {
        return participant;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setParticipant(ParticipantRequest participant) {
        this.participant = participant;
    }
}

package elizabethbarcena.EventHubAPI.mapper;

import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.dto.response.TicketResponse;
import elizabethbarcena.EventHubAPI.entity.Participant;
import elizabethbarcena.EventHubAPI.entity.Ticket;

public class TicketMapper {

    private TicketMapper() {
    }

    public static TicketResponse toResponse(Ticket ticket, Participant participant) {

        ParticipantResponse participantResponse = new ParticipantResponse(
                participant.getId(),
                participant.getName(),
                participant.getEmail()
        );

        return new TicketResponse(
                ticket.getId(),
                ticket.getEvent().getId(),
                ticket.getEvent().getName(),
                participantResponse,
                ticket.getPurchaseDate()
        );
    }

}
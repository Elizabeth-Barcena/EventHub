package elizabethbarcena.EventHubAPI.service;

import elizabethbarcena.EventHubAPI.dto.request.TicketRequest;
import elizabethbarcena.EventHubAPI.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {

    TicketResponse purchaseTicket(TicketRequest request);


    List<TicketResponse> getTicketsByParticipantId(Long participantId);


    List<TicketResponse> getTicketsByParticipantEmail(String email);
    long getTicketsSoldByEvent(Long eventId);
}

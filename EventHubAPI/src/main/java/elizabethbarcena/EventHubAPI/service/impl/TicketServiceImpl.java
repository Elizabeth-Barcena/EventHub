package elizabethbarcena.EventHubAPI.service.impl;


import elizabethbarcena.EventHubAPI.dto.request.TicketRequest;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.dto.response.TicketResponse;
import elizabethbarcena.EventHubAPI.entity.Event;
import elizabethbarcena.EventHubAPI.entity.Participant;
import elizabethbarcena.EventHubAPI.entity.Ticket;
import elizabethbarcena.EventHubAPI.exceptions.EventInvalidException;
import elizabethbarcena.EventHubAPI.exceptions.EventNotFoundException;
import elizabethbarcena.EventHubAPI.exceptions.EventSoldOutException;
import elizabethbarcena.EventHubAPI.mapper.TicketMapper;
import elizabethbarcena.EventHubAPI.repository.EventRepository;
import elizabethbarcena.EventHubAPI.repository.TicketRepository;
import elizabethbarcena.EventHubAPI.service.ParticipantService;
import elizabethbarcena.EventHubAPI.service.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final ParticipantService participantService;

    public TicketServiceImpl(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            ParticipantService participantService
    ) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.participantService = participantService;
    }

    @Override
    @Transactional
    public TicketResponse purchaseTicket(TicketRequest request) {

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() ->
                        new EventNotFoundException(request.getEventId())
                );

        if (event.getCapacity() <= 0) {
            throw new EventSoldOutException(event.getId());
        }

        Participant participant =
                participantService.getOrCreateParticipant(request.getParticipant());

        Ticket ticket = new Ticket(event, participant);

        event.setCapacity(event.getCapacity() - 1);

        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketMapper.toResponse(savedTicket, participant);
    }

    @Override
    @Transactional
    public List<TicketResponse> getTicketsByParticipantId(Long participantId) {
        return ticketRepository.findByParticipantId(participantId)
                .stream()
                .map(ticket -> TicketMapper.toResponse(ticket, ticket.getParticipant()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TicketResponse> getTicketsByParticipantEmail(String email) {
        return ticketRepository.findByParticipantEmail(email)
                .stream()
                .map(ticket -> TicketMapper.toResponse(ticket, ticket.getParticipant()))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public long getTicketsSoldByEvent(Long eventId) {
        return ticketRepository.countByEventId(eventId);
    }
}

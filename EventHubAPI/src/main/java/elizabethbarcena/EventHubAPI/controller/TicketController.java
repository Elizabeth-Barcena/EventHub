package elizabethbarcena.EventHubAPI.controller;

import elizabethbarcena.EventHubAPI.dto.request.TicketRequest;
import elizabethbarcena.EventHubAPI.dto.response.TicketResponse;
import elizabethbarcena.EventHubAPI.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<TicketResponse> purchaseTicket(
            @Valid @RequestBody TicketRequest request
    ) {
        TicketResponse response = ticketService.purchaseTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<TicketResponse>> getTicketsByParticipantId(
            @PathVariable Long participantId
    ) {
        return ResponseEntity.ok(
                ticketService.getTicketsByParticipantId(participantId)
        );
    }


    @GetMapping("/participant")
    public ResponseEntity<List<TicketResponse>> getTicketsByParticipantEmail(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(
                ticketService.getTicketsByParticipantEmail(email)
        );
    }
    @GetMapping("/count/event/{eventId}")
    public ResponseEntity<Long> getTicketsSoldByEvent(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(ticketService.getTicketsSoldByEvent(eventId));
    }

}

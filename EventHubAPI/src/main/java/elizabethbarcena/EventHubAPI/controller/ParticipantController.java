package elizabethbarcena.EventHubAPI.controller;

import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponse> getParticipantById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<ParticipantResponse> getParticipantByEmail(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(participantService.getParticipantByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponse>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }
}
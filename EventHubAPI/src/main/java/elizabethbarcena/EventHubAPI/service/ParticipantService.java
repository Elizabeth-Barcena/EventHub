package elizabethbarcena.EventHubAPI.service;

import elizabethbarcena.EventHubAPI.dto.request.ParticipantRequest;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.entity.Participant;

import java.util.List;

public interface ParticipantService {
    Participant getOrCreateParticipant(ParticipantRequest request);

    ParticipantResponse getParticipantById(Long id);

    ParticipantResponse getParticipantByEmail(String email);

    List<ParticipantResponse> getAllParticipants();
}

package elizabethbarcena.EventHubAPI.service;

import elizabethbarcena.EventHubAPI.dto.request.ParticipantRequest;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;

import java.util.List;

public interface ParticipantService {
    ParticipantResponse createOrGetParticipant(ParticipantRequest request);

    ParticipantResponse getParticipantById(Long id);

    ParticipantResponse getParticipantByEmail(String email);

    List<ParticipantResponse> getAllParticipants();
}

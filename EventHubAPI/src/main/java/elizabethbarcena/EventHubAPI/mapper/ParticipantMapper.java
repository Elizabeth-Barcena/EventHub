package elizabethbarcena.EventHubAPI.mapper;

import elizabethbarcena.EventHubAPI.dto.request.ParticipantRequest;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.entity.Participant;

public class ParticipantMapper {

    private ParticipantMapper() {
    }

    public static Participant toEntity(ParticipantRequest request) {
        Participant participant = new Participant();
        participant.setName(request.getName());
        participant.setEmail(request.getEmail());
        return participant;
    }

    public static ParticipantResponse toResponse(Participant participant) {
        return new ParticipantResponse(
                participant.getId(),
                participant.getName(),
                participant.getEmail()
        );
    }
}
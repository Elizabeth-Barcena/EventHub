package elizabethbarcena.EventHubAPI.exceptions;
public class ParticipantNotFoundException extends BusinessException {

    public ParticipantNotFoundException(Long id) {
        super(ErrorCode.PARTICIPANT_NOT_FOUND,"Participant not found with id: " + id);
    }

    public ParticipantNotFoundException(String email) {
        super(ErrorCode.PARTICIPANT_NOT_FOUND, "Participant not found with email: " + email);
    }
}

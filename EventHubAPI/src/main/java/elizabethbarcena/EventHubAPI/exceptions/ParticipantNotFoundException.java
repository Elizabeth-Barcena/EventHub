package elizabethbarcena.EventHubAPI.exceptions;
public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(Long id) {
        super("Participant not found with id: " + id);
    }

    public ParticipantNotFoundException(String email) {
        super("Participant not found with email: " + email);
    }
}

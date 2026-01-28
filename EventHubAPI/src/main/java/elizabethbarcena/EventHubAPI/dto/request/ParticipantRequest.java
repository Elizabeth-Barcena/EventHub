package elizabethbarcena.EventHubAPI.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ParticipantRequest {
    @NotBlank(message = "Event name must not be blank")
    private String name;
    @NotBlank(message = "Event name must not be blank")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

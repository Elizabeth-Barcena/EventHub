package elizabethbarcena.EventHubAPI.controller;

import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.exceptions.ParticipantNotFoundException;
import elizabethbarcena.EventHubAPI.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParticipantController.class)
class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    @Test
    void shouldGetParticipantByIdSuccessfully() throws Exception {

        ParticipantResponse response =
                new ParticipantResponse(1L, "Ana", "ana@email.com");

        when(participantService.getParticipantById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@email.com"));
    }

    @Test
    void shouldReturn404WhenParticipantNotFound() throws Exception {

        when(participantService.getParticipantById(100L))
                .thenThrow(new ParticipantNotFoundException(100L));

        mockMvc.perform(get("/participants/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode")
                        .value("PARTICIPANT_NOT_FOUND"));
    }
}

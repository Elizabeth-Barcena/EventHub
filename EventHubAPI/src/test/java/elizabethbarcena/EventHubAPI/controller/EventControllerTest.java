package elizabethbarcena.EventHubAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import elizabethbarcena.EventHubAPI.dto.response.EventResponse;
import elizabethbarcena.EventHubAPI.exceptions.EventHasTicketsException;
import elizabethbarcena.EventHubAPI.exceptions.EventNotFoundException;
import elizabethbarcena.EventHubAPI.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEventSuccessfully() throws Exception {

        EventResponse response = new EventResponse(
                1L,
                "Evento Java",
                LocalDateTime.now().plusDays(1),
                "São Paulo",
                100
        );

        when(eventService.createEvent(any()))
                .thenReturn(response);

        String requestJson = """
            {
              "name": "Evento Java",
              "date": "2030-01-01T10:00:00",
              "location": "São Paulo",
              "capacity": 100
            }
        """;

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Evento Java"))
                .andExpect(jsonPath("$.capacity").value(100));
    }

    @Test
    void shouldReturn400WhenEventIsInvalid() throws Exception {

        String invalidJson = """
            {
              "name": "",
              "date": "2020-01-01T10:00:00",
              "location": "",
              "capacity": -1
            }
        """;

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode")
                        .value("VALIDATION_ERROR"));
    }

    @Test
    void shouldReturn404WhenEventNotFound() throws Exception {

        when(eventService.getEventById(99L))
                .thenThrow(new EventNotFoundException(99L));

        mockMvc.perform(get("/events/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode")
                        .value("EVENT_NOT_FOUND"));
    }
    @Test
    void shouldReturn409WhenDeletingEventWithSoldTickets() throws Exception {

        Long eventId = 1L;

        doThrow(new EventHasTicketsException(eventId))
                .when(eventService).deleteEvent(eventId);

        mockMvc.perform(delete("/events/{id}", eventId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("EVENT_INVALID"))
                .andExpect(jsonPath("$.message")
                        .value("Event with id 1 cannot be deleted because it has sold tickets"));
    }
    @Test
    void shouldDeleteEventSuccessfully() throws Exception {

        mockMvc.perform(delete("/events/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}

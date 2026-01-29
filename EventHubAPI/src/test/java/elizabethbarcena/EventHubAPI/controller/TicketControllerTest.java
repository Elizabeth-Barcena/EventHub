package elizabethbarcena.EventHubAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import elizabethbarcena.EventHubAPI.controller.TicketController;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.dto.response.TicketResponse;
import elizabethbarcena.EventHubAPI.exceptions.EventSoldOutException;
import elizabethbarcena.EventHubAPI.exceptions.ParticipantNotFoundException;
import elizabethbarcena.EventHubAPI.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldPurchaseTicketSuccessfully() throws Exception {

        TicketResponse response = new TicketResponse(
                1L,
                1L,
                "Evento Teste",
                new ParticipantResponse(1L, "Ana", "ana@email.com"),
                LocalDateTime.now()
        );

        when(ticketService.purchaseTicket(any()))
                .thenReturn(response);

        String requestJson = """
            {
              "eventId": 1,
              "participant": {
                "name": "Ana",
                "email": "ana@email.com"
              }
            }
        """;

        mockMvc.perform(post("/tickets/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("Evento Teste"))
                .andExpect(jsonPath("$.participant.email").value("ana@email.com"));
    }
    @Test
    void shouldReturn409WhenEventIsSoldOut() throws Exception {

        when(ticketService.purchaseTicket(any()))
                .thenThrow(new EventSoldOutException(1L));

        String requestJson = """
        {
          "eventId": 1,
          "participant": {
            "name": "Ana",
            "email": "ana@email.com"
          }
        }
    """;

        mockMvc.perform(post("/tickets/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("EVENT_SOLD_OUT"));
    }
    @Test
    void shouldReturn404WhenParticipantDoesNotExist() throws Exception {

        when(ticketService.getTicketsByParticipantId(100L))
                .thenThrow(new ParticipantNotFoundException(100L));

        mockMvc.perform(get("/tickets/participant/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("PARTICIPANT_NOT_FOUND"));
    }
    @Test
    void shouldReturnEmptyListWhenParticipantHasNoTickets() throws Exception {

        when(ticketService.getTicketsByParticipantId(1L))
                .thenReturn(List.of());

        mockMvc.perform(get("/tickets/participant/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


}

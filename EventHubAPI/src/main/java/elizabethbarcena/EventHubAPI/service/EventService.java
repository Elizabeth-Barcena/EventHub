package elizabethbarcena.EventHubAPI.service;



import elizabethbarcena.EventHubAPI.dto.request.EventRequest;
import elizabethbarcena.EventHubAPI.dto.response.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(Long id);

    EventResponse updateEvent(Long id, EventRequest request);

    void deleteEvent(Long id);
}
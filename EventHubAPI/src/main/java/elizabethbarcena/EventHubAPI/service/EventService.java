package elizabethbarcena.EventHubAPI.service;



import elizabethbarcena.EventHubAPI.dto.EventRequest;
import elizabethbarcena.EventHubAPI.dto.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(Long id);

    EventResponse updateEvent(Long id, EventRequest request);

    void deleteEvent(Long id);
}
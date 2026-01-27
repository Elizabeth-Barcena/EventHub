package elizabethbarcena.EventHubAPI.mapper;

import elizabethbarcena.EventHubAPI.Entity.Event;
import elizabethbarcena.EventHubAPI.dto.EventRequest;
import elizabethbarcena.EventHubAPI.dto.EventResponse;

public class EventMapper {

    private EventMapper() {
    }

    public static Event request(EventRequest dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setCapacity(dto.getCapacity());
        return event;
    }

    public static EventResponse response(Event event) {
        return new EventResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getLocation(),
                event.getCapacity()
        );
    }

    public static void update(Event event, EventRequest dto) {
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setCapacity(dto.getCapacity());
    }
}

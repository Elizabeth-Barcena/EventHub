package elizabethbarcena.EventHubAPI.service.impl;

import elizabethbarcena.EventHubAPI.entity.Event;
import elizabethbarcena.EventHubAPI.dto.EventRequest;
import elizabethbarcena.EventHubAPI.dto.EventResponse;
import elizabethbarcena.EventHubAPI.exceptions.EventNotFoundException;
import elizabethbarcena.EventHubAPI.mapper.EventMapper;
import elizabethbarcena.EventHubAPI.repository.EventRepository;
import elizabethbarcena.EventHubAPI.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponse createEvent(EventRequest request) {
        Event event = EventMapper.request(request);
        Event savedEvent = eventRepository.save(event);
        return EventMapper.response(savedEvent);
    }

    @Override
    public List<EventResponse> getAllEvents(){
        return eventRepository.findAll()
                .stream()
                .map(EventMapper::response)
                .collect(Collectors.toList());
    }
    @Override
    public EventResponse getEventById(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        return EventMapper.response(event);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        EventMapper.update(event, request);

        Event updatedEvent = eventRepository.save(event);
        return EventMapper.response(updatedEvent);
    }


    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        eventRepository.deleteById(id);
    }
}
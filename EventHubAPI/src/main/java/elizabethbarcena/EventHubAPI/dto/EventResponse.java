package elizabethbarcena.EventHubAPI.dto;

import java.time.LocalDateTime;

public class EventResponse {

    private Long id;
    private String name;
    private LocalDateTime date;
    private String location;
    private Integer capacity;

    public EventResponse(Long id, String name, LocalDateTime date, String location, Integer capacity) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }
}

package elizabethbarcena.EventHubAPI.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    public Ticket() {
    }

    public Ticket(Event event, Participant participant) {
        this.event = event;
        this.participant = participant;
        this.purchaseDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}

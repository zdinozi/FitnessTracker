package pl.wsb.fitnesstracker.event.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "user_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    public UserEvent(User user, Event event, LocalDate registrationDate) {
        this.user = user;
        this.event = event;
        this.registrationDate = registrationDate;
    }

    public UserEvent(User user, Event event) {
        this.user = user;
        this.event = event;
        this.registrationDate = LocalDate.now();
    }
}

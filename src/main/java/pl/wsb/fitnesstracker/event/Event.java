package pl.wsb.fitnesstracker.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table
public class Event {

    @Id
    private Long id;
}

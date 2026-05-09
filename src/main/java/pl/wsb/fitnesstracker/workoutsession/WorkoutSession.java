package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// TODO: Define the Event entity with appropriate fields and annotations
public class WorkoutSession {

    @Id
    private int id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long trainingId;
    private String timestamp;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double altitude;
}

package pl.wsb.fitnesstracker.training.session;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;

@Entity
@Table(name = "Workout_Session")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "startLatitude", nullable = false)
    private Double startLatitude;

    @Column(name = "startLongitude", nullable = false)
    private Double startLongitude;

    @Column(name = "endLatitude", nullable = false)
    private Double endLatitude;

    @Column(name = "endLongitude", nullable = false)
    private Double endLongitude;

    @Column(name = "altitude", nullable = false)
    private Double altitude;

}

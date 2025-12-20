package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a user within the fitness tracker system.
 * <p>
 * This entity stores personal and contact details of a user,
 * as well as their associated training sessions.
 * Each user has a unique email address used for identification.
 * </p>
 *
 * <p>
 * The {@link #trainings} field defines a one-to-many relationship with
 * the {@link Training} entity. When a user is removed, all associated
 * trainings are automatically deleted.
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unique identifier for the user.
     * <p>
     * The value is generated automatically by the database using
     * the {@link GenerationType#IDENTITY} strategy.
     * </p>
     * May be {@code null} when a user has not yet been persisted.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * The user's first name.
     */
    @Column
    private String firstName;

    /**
     * The user's last name.
     */
    @Column
    private String lastName;

    /**
     * The user's date of birth.
     * Cannot be {@code null}.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * The user's email address, which must be unique across all users.
     * Cannot be {@code null}.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * List of training sessions associated with this user.
     * <p>
     * When the user is deleted, all related trainings are also removed.
     * Lazy fetching is used to avoid unnecessary loading of training data.
     * </p>
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Training> trainings;

    /**
     * Constructs a new {@code User} without an ID (for creating new entities).
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param birthdate The user's birthdate.
     * @param email     The user's unique email address.
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Constructs a new {@code User} with a specified ID (e.g., for updates or migrations).
     *
     * @param id        The unique identifier of the user.
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param birthdate The user's birthdate.
     * @param email     The user's unique email address.
     */
    public User(
            final Long id,
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}

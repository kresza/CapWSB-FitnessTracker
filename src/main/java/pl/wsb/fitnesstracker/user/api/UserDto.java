package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing a user within the system.
 * <p>
 * This record is typically used to transfer user-related information
 * between application layers, particularly between the backend and the frontend.
 * </p>
 *
 * @param id        The unique identifier of the user. May be {@code null} for new users (e.g., before persistence).
 * @param firstName The user's given name. Cannot be {@code null}.
 * @param lastName  The user's family name. Cannot be {@code null}.
 * @param birthdate The user's date of birth, formatted as {@code yyyy-MM-dd} when serialized to JSON.
 * @param email     The user's email address. Cannot be {@code null}.
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}

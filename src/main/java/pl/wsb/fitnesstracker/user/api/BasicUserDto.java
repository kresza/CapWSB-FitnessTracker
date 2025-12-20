package pl.wsb.fitnesstracker.user.api;

/**
 * A simplified Data Transfer Object (DTO) representing basic user information.
 * <p>
 * This record is typically used when only minimal user details are needed,
 * for example in lists, references, or lightweight API responses,
 * without exposing sensitive or unnecessary fields.
 * </p>
 *
 * @param id        The unique identifier of the user.
 * @param firstName The user's given name.
 * @param lastName  The user's family name.
 */
public record BasicUserDto(Long id, String firstName, String lastName) { }

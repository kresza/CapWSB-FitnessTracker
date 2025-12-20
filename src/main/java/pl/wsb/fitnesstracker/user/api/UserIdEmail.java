package pl.wsb.fitnesstracker.user.api;

/**
 * A lightweight Data Transfer Object (DTO) containing a user's identifier and email address.
 * <p>
 * This record is useful for operations where only the user's identity and contact
 * information are required, such as authentication responses, lookups, or references
 * in other resources.
 * </p>
 *
 * @param id    The unique identifier of the user.
 * @param email The user's email address.
 */
public record UserIdEmail(Long id, String email) { }

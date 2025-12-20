package pl.wsb.fitnesstracker.training.api;


import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a training session.
 * <p>
 * This record transfers essential training information between layers,
 * including the associated user, timing, activity type, and performance metrics.
 * </p>
 *
 * @param id            The unique identifier of the training session.
 * @param user          Basic information about the user who performed the training.
 * @param startTime     The start time of the training session.
 * @param endTime       The end time of the training session.
 * @param activityType  The type of activity performed (e.g., running, cycling).
 * @param distance      The total distance covered (in kilometers). May be 0 if not applicable.
 * @param averageSpeed  The average speed during the training (in km/h). May be 0 if not applicable.
 */
public record TrainingDto(
        Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) { }

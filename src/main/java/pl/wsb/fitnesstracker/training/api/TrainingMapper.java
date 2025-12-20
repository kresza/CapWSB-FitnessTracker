package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
@RequiredArgsConstructor
public class TrainingMapper {
    private final UserMapper userMapper;

     public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                toUserDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

     private UserDto toUserDto(User user) {
        return userMapper.toUserDto(user);
    }
}

package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmail;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailSender mailSender;

    UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper, MailSender mailSender) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailSender = mailSender;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<BasicUserDto> findAllUsersWithBasicInformation() {
        return userRepository.findAllUsersWithBasicData().stream()
                .map(userMapper::toBasicUserDto)
                .toList();
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.toUserDto(user);
    }

    @Transactional
    public User createUser(final UserDto user) {
        User entity = new User(user.firstName(), user.lastName(), user.birthdate(), user.email());
        return userRepository.save(entity);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long id, UserDto userDto) {
        User user =
                new User(
                        id,
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
        userRepository.save(user);
    }

    public List<UserIdEmail> findUserByEmail(String email) {
        List<User> users = userRepository.findUsersByEmailContainingIgnoreCase(email);
        return users.stream().map(user -> new UserIdEmail(user.getId(), user.getEmail())).toList();
    }

    public List<UserDto> findAllUsersOlderThan(LocalDate time) {
        return userRepository.findByBirthdateGreaterThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public void generateReport(Date startDate, Date endDate) {
        List<User> usersWithTrainings =
                userRepository.findUsersWithWeeklyTrainings(startDate, endDate);

        log.info("Users trainings from {} to {}", startDate, endDate);
        usersWithTrainings.forEach(user ->{
            logTrainings(user);
            mailSender.send(new EmailDto(user.getEmail(), "Training report", "Number of trainings: " + user.getTrainings().size()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } );
    }

    private void logTrainings(User user) {
        log.info("User name: {} {}", user.getFirstName(), user.getLastName());
        log.info("User Trainings:");
        AtomicLong trainingIdx = new AtomicLong(1L);
        user.getTrainings()
                .forEach(
                        training -> {
                            log.info("Training - {}", trainingIdx.getAndIncrement());
                            log.info("Distance: {}", training.getDistance());
                            log.info("Started at: {}", training.getStartTime());
                            log.info("Ended at: {}", training.getEndTime());
                            log.info("Avg Speed: {}", training.getAverageSpeed());
                            log.info("Activity type: {}", training.getActivityType());
                        });
        log.info("");
        log.info("--------------------------------------");
    }
}

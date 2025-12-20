package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getTrainings();
    }

    @GetMapping("/{userId}")
    List<TrainingDto> getUserTrainings(@PathVariable long userId) {
        return trainingService.getUserTrainings(userId);
    }
}

package pl.wsb.fitnesstracker.training.api;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t WHERE t.user.id =:userId")
     List<Training> findAllByUserId(@PathParam("userId") Long userId);




}

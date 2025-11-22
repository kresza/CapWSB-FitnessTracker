package pl.wsb.fitnesstracker.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public String findEventById(int id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(eventRepository.findEventById(id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String findEventByName(String name) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(eventRepository.findEventByName(name));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}

package pl.wsb.fitnesstracker.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping( "/event/{id}")
    public String findEvent(@PathVariable int id) {
        return eventService.findEventById(id);
    }

//    @GetMapping( "/event/{name}")
//    public String findEvent(@PathVariable String name) {
//        return eventService.findEventByName(name);
//    }
}

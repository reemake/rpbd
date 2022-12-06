package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rpbd.lab.services.EventService;
import rpbd.lab.services.UserService;

import java.security.Principal;

@Controller
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String eventsPage(Model model, Principal principal) {
        model.addAttribute("eventService", eventService);
        model.addAttribute("events", eventService.getEvents());
        return "events";
    }

}

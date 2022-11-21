package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rpbd.lab.entities.Event;
import rpbd.lab.repositories.EventRepository;

@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "userhome";
    }

    @PostMapping("/events")
    public String saveEvent(Event event) {
        eventRepository.save(event);
        return "redirect:/home";
    }
}

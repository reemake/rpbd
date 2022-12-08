package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rpbd.lab.entities.Event;
import rpbd.lab.services.EventService;
import rpbd.lab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String eventsPage(Model model) {
        model.addAttribute("eventService", eventService);
        model.addAttribute("events", eventService.getEvents());
        return "events";
    }

    @GetMapping("/event/create")
    public String eventCreatePage(Model model) {
        model.addAttribute("event", new Event());
        return "eventCreate";
    }

    @PostMapping("/event/create")
    public String eventCreateSubmit(@ModelAttribute("event") Event event,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {

        if (bindingResult.hasErrors()) {
            return "eventCreate";
        }

        if (!eventService.createEvent(event, principal)){
            boolean creationError = true;
            model.addAttribute("creationError", creationError);
            return "eventCreate";
        }

        eventService.createEvent(event, principal);
        model.addAttribute("event", event);
        return "redirect:/events";
    }

    @PostMapping("/event/assign")
    public String assignToEvent(@RequestParam Integer eventId,
                                Principal principal,
                                Model model) {

        if (!eventService.assignToEvent(eventId, principal)){
            boolean assignError = true;
            model.addAttribute("assignError", assignError);
            return "events";
        }

        eventService.assignToEvent(eventId, principal);
        return "redirect:/events";
    }

    @PostMapping("/event/unassign")
    public String unassignFromEvent(@RequestParam Integer eventId,
                                  Principal principal,
                                  Model model) {

        if (!eventService.unassignFromEvent(eventId, principal)){
            boolean unassignError = true;
            model.addAttribute("unassignError", unassignError);
            return "events";
        }

        return "redirect:/events";
    }

    @GetMapping("/events/search")
    public String eventSearch(Model model, String keyword) {
        if (keyword != null) {
            List<Event> events = eventService.getEventsByKeyword(keyword);
            model.addAttribute("events", events);
        }
        else {
            List<Event> events = eventService.getEvents();
            model.addAttribute("events", events);
        }
        model.addAttribute("eventService", eventService);

        return "events";
    }
}

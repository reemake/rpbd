package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rpbd.lab.services.EventService;
import rpbd.lab.services.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/home")
    public String userHomePage(Model model) {
        model.addAttribute("eventService", eventService);
        model.addAttribute("organizedEvents", eventService.getUsersCreatedEventsByLogin());
        model.addAttribute("attendingEvents", eventService.getUsersAttendingEventsByLogin());
        return "userhome";
    }

    @PostMapping("/user/events/organized/delete")
    public String deleteEvent(@RequestParam Integer eventId, Model model) {
        eventService.deleteEventById(eventId);
        return "redirect:/home";
    }

    @PostMapping("/user/events/attending/unassign")
    public String unassignFromEvent(@RequestParam Integer eventId,
                                    Principal principal,
                                    Model model) {

        if (!eventService.unassignFromEvent(eventId, principal)){
            boolean unassignError = true;
            model.addAttribute("unassignError", unassignError);
            return "events";
        }

        return "redirect:/home";
    }
}

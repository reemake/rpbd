package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rpbd.lab.services.EventService;
import rpbd.lab.services.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/admin/home")
    public String usersAndEventsLists(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("events", eventService.getEvents());
        return "adminhome";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam String login, Model model) {
        userService.deleteUserByLogin(login);
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/events/delete")
    public String deleteEvent(@RequestParam Integer eventId, Model model) {
        eventService.deleteEventById(eventId);
        return "redirect:/admin/home";
    }
}
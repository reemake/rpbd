package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rpbd.lab.repositories.EventRepository;
import rpbd.lab.services.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/admin/home")
    public String usersAndEventsLists(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("events", eventRepository.findAll());
        return "adminhome";
    }

    @PostMapping("/deleteUser")
    public String deleteUserOrEvent(@RequestParam String login, Model model) {
        userService.deleteUserByLogin(login);
        return "redirect:/admin/home";
    }
}
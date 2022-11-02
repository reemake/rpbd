package rpbd.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rpbd.lab.entities.User;
import rpbd.lab.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
    }
}

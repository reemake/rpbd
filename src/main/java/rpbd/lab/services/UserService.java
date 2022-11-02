package rpbd.lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rpbd.lab.entities.Role;
import rpbd.lab.entities.User;
import rpbd.lab.repositories.RoleRepository;
import rpbd.lab.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        if (isUserExist(user.getLogin()))
            return false;
        else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
    }

    public boolean isUserExist(String login) {
        User userByLogin = userRepository.findById(login).orElse(null);
        if (userByLogin != null)
            return true;
        else
            return false;
    }

    public boolean deleteUserByLogin(String userLogin) {
        if (userRepository.findById(userLogin).isPresent()) {
            userRepository.deleteById(userLogin);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return user;
    }
}

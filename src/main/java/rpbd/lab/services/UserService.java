package rpbd.lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

import java.util.Collection;
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
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
    }

    public boolean isUserExist(String login) {
        return userRepository.findById(login).isPresent();
    }

    public boolean deleteUserByLogin(String login) {
        if (isUserExist(login)) {
            userRepository.deleteById(login);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findById(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не найден!"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}

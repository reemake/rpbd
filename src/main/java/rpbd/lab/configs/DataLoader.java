package rpbd.lab.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import rpbd.lab.entities.Role;
import rpbd.lab.entities.User;
import rpbd.lab.repositories.RoleRepository;
import rpbd.lab.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
        roleRepository.save(new Role(1L, "ROLE_ADMIN", null));
        roleRepository.save(new Role(2L, "ROLE_USER", null));
    }
}

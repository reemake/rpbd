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

        Set<Role> roles1 = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();
        roles1.add(roleRepository.findByName("ROLE_ADMIN"));
        roles2.add(roleRepository.findByName("ROLE_USER"));
        userRepository.save(new User("reemake", "$2a$12$.ticNFlSiEtAZ26UYB4UIu9BeOQ3em93JefxDpI4R8Deuecw6ICKO", "admin@mail.ru", roles1));
        userRepository.save(new User("lox", "$2a$12$WaOOUb7oYuwy7f54/PsK5OS1Kd2/wyBVih8i26/AbpGz.HyCdPYFi", "lox@mail.ru", roles2));
    }
}

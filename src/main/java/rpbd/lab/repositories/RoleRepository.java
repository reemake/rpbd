package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rpbd.lab.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

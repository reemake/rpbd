package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rpbd.lab.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM Roles r WHERE r.name like :name", nativeQuery = true)
    Role findByName(String name);
}

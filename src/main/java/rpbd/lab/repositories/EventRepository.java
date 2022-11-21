package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rpbd.lab.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}

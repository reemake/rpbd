package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rpbd.lab.entities.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT e FROM Event e where e.organizer.login = :userLogin", nativeQuery = true)
    List<Event> getUserCreatedEvents(String userLogin);
}

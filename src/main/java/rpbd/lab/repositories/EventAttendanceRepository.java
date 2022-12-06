package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rpbd.lab.entities.Event;
import rpbd.lab.entities.EventAttendance;
import rpbd.lab.entities.UserEventKey;

import java.util.List;

@Repository
public interface EventAttendanceRepository extends JpaRepository<EventAttendance, UserEventKey> {
    @Query(value = "SELECT e.event FROM EventAttendance e where e.id.userId = :userLogin", nativeQuery = true)
    List<Event> getUserAttendedEvents(String userLogin);
}

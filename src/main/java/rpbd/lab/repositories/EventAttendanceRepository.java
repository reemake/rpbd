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
    @Query(value = "SELECT e.event_id FROM event_attendance e where e.attender_login = :userLogin", nativeQuery = true)
    List<Integer> getUserAttendingEventsIds(String userLogin);
}

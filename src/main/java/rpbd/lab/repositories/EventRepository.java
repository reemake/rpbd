package rpbd.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rpbd.lab.entities.Event;

import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT * FROM Event e WHERE e.organizer_login = :userLogin", nativeQuery = true)
    List<Event> getUserCreatedEvents(String userLogin);

    @Query(value = "SELECT * FROM Event e WHERE e.id IN :ids", nativeQuery = true)
    List<Event> getUserAttendingEvents(@Param("ids") Collection<Integer> ids);

    @Query(value = "SELECT * FROM Event e WHERE e.name like %:keyword% or e.description like %:keyword% or e.location like %:keyword%", nativeQuery = true)
    List<Event> getEventsByKeyword(String keyword);
}


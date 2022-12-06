package rpbd.lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rpbd.lab.entities.Event;
import rpbd.lab.entities.EventAttendance;
import rpbd.lab.entities.User;
import rpbd.lab.entities.UserEventKey;
import rpbd.lab.repositories.EventAttendanceRepository;
import rpbd.lab.repositories.EventRepository;
import rpbd.lab.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventAttendanceRepository eventAttendanceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public boolean createEvent(Event event, Principal principal) {
        String userLogin = principal.getName();
        User organizer = userRepository.findById(userLogin).orElse(null);
        if (organizer == null)
            return false;
        event.setOrganizer(organizer);
        eventRepository.save(event);
        return true;
    }

    public boolean isEventExist(Integer id) {
        return eventRepository.findById(id).isPresent();
    }

    public boolean deleteEventById(Integer id) {
        if (isEventExist(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean assignToEvent(Integer eventId, Principal principal) {
        Event event = eventRepository.findById(eventId).orElse(null);
        User attender = userRepository.findById(principal.getName()).orElse(null);
        if (event == null || attender == null)
            return false;
        EventAttendance eventAttendance = new EventAttendance();
        eventAttendance.setId(new UserEventKey(event.getId(), principal.getName()));
        eventAttendance.setEvent(event);
        eventAttendance.setUser(attender);
        eventAttendanceRepository.save(eventAttendance);
        return true;
    }

    public boolean unassignFromEvent(Integer eventId, Principal principal) {
        if (isEventExist(eventId)) {
            eventAttendanceRepository.deleteById(new UserEventKey(eventId, principal.getName()));
            return true;
        }
        return false;
    }

    public boolean isAssignedOnEvent(Integer eventId, Principal principal) {
        List<Event> attendedEvents = eventAttendanceRepository.getUserAttendedEvents(principal.getName());
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Мероприятие с таким ID не найдено!");
        if (attendedEvents.contains(event))
            return true;
        return false;
    }

    public List<Event> getUsersCreatedEventsByLogin(String userLogin) {
        return eventRepository.getUserCreatedEvents(userLogin);
    }

    public List<Event> getUsersAttendedEventsByLogin(String userLogin) {
        return eventAttendanceRepository.getUserAttendedEvents(userLogin);
    }

}

package rpbd.lab.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventAttendance {

    @EmbeddedId
    private UserEventKey id;

    @ManyToOne
    @MapsId("eventId")
    private Event event;

    @ManyToOne
    @MapsId("attenderLogin")
    private User attender;
}

package rpbd.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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

//    @ManyToOne
//    @MapsId("userLogin")
//    private User user;
}

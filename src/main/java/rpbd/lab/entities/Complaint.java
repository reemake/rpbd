package rpbd.lab.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @EmbeddedId
    private UserEventKey id;

    @NonNull
    private String text;

    private String status;

    @ManyToOne
    @MapsId("eventId")
    @ToString.Exclude
    private Event event;

    @ManyToOne
    @MapsId("userLogin")
    @ToString.Exclude
    private User complainer;

    @ManyToOne
    @ToString.Exclude
    private User resolver;
}

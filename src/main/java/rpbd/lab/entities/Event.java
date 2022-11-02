package rpbd.lab.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    @ToString.Exclude
    private Location location;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User organizer;


    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<EventAttendance> attenders;


//    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
//    @ToString.Exclude
//    private Set<Complaint> complaints;

}

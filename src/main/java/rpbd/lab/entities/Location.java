package rpbd.lab.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    private Set<Event> events;
}

package rpbd.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @Column(unique = true, name = "login")
    private String login;

    private String password;

    private String name;

    @Transient
    private ERole role;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<EventAttendance> eventAttendances;

    @OneToMany(mappedBy = "organizer")
    private Set<Event> organizedEvents;

}

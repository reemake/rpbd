package rpbd.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @Column(unique = true, name = "login")
    private String login;

    private String password;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "attender", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<EventAttendance> eventAttendances;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Event> organizedEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login != null && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

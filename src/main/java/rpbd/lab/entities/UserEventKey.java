package rpbd.lab.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserEventKey implements Serializable {
    private int eventId;
    private String userLogin;
}

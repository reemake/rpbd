package rpbd.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class UserEventKey implements Serializable {
    private Integer eventId;
    private String attenderLogin;
}

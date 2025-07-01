package aibook.domain;

import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UserRegistered extends AbstractEvent {

    private Long id;
    private String email;
    private String userName;
    private String password;
    private Boolean isSubscription;

}

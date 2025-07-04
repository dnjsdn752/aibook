package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserRegistered extends AbstractEvent {

    private Long id;
    private String email;
    private String userName;
    private String password;

    // public UserRegistered(User aggregate) {
    //     super(aggregate);
    //     this.id = aggregate.getId();
    //     this.email = aggregate.getEmail();
    //     this.userName = aggregate.getUserName();
    //     this.password = aggregate.getPassword();
    // }

    public UserRegistered(Long id, String email, String userName, String password) {
        super();
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }


    public UserRegistered() {
        super();
    }
}
//>>> DDD / Domain Event

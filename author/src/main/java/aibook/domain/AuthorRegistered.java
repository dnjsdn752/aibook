package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorRegistered extends AbstractEvent {

    private Long id;
    private String email;
    private String authorName;
    private String introduction;
    private String featuredWorks;
    private Boolean isApprove;

    public AuthorRegistered(Author aggregate) {
        super(aggregate);
    }

    public AuthorRegistered() {
        super();
    }
}
//>>> DDD / Domain Event

package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorDisApproved extends AbstractEvent {

    private Long id;
    private Boolean isApprove;

    public AuthorDisApproved(Author aggregate) {
        super(aggregate);
    }

    public AuthorDisApproved() {
        super();
    }
}
//>>> DDD / Domain Event

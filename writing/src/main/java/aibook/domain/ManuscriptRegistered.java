package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ManuscriptRegistered extends AbstractEvent {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName;

    public ManuscriptRegistered(Manuscript aggregate) {
        super(aggregate);
    }

    public ManuscriptRegistered() {
        super();
    }
}
//>>> DDD / Domain Event

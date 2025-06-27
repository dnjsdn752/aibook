package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AiRequest extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String authorName;

    public AiRequest(Manuscript aggregate) {
        super(aggregate);
    }

    public AiRequest() {
        super();
    }
}
//>>> DDD / Domain Event

package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PublishingRequested extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private Boolean status;
    private String authorName;
    private Date date;
    private String aiImage;
    private String aiSummary;

    public PublishingRequested(Manuscript aggregate) {
        super(aggregate);
    }

    public PublishingRequested() {
        super();
    }
}
//>>> DDD / Domain Event

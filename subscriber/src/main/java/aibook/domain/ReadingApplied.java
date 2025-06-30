package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadingApplied extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;

    public ReadingApplied(Reading aggregate) {
        super(aggregate);
    }

    public ReadingApplied() {
        super();
    }
}
//>>> DDD / Domain Event

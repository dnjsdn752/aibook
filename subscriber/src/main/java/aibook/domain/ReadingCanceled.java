package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadingCanceled extends AbstractEvent {

    private Long id;
    private UserId userId;
    private BookId bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;

    public ReadingCanceled(Reading aggregate) {
        super(aggregate);
    }

    public ReadingCanceled() {
        super();
    }
}
//>>> DDD / Domain Event

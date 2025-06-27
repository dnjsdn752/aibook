package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OutOfPoint extends AbstractEvent {

    private Long id;
    private Integer point;
    private UserId userId;

    public OutOfPoint(Point aggregate) {
        super(aggregate);
    }

    public OutOfPoint() {
        super();
    }
}
//>>> DDD / Domain Event

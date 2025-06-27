package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class RegisterPointGained extends AbstractEvent {

    private Long id;
    private Integer point;
    private Boolean isSubscribe;
    private Long readingId;
    private Long userId;

    public RegisterPointGained(Point aggregate) {
        super(aggregate);
    }

    public RegisterPointGained() {
        super();
    }
}
//>>> DDD / Domain Event

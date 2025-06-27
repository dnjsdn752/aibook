package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionBought extends AbstractEvent {

    private Long id;
    private Boolean isSubscription;

    public SubscriptionBought(User aggregate) {
        super(aggregate);
    }

    public SubscriptionBought() {
        super();
    }
}
//>>> DDD / Domain Event

package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionUpdate extends AbstractEvent{
    private Long id;
    private Integer totalPoint;
    private Long userId;
    private Boolean isSubscribe;

}

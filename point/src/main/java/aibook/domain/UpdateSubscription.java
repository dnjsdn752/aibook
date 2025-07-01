package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

@Data
@ToString
public class UpdateSubscription extends AbstractEvent {

    private Long id;
    private Boolean isSubscription;

    public UpdateSubscription(Point aggregate) {
        super(aggregate);
    }

    public UpdateSubscription() {
        super();
    }
}
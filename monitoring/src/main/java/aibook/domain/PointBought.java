package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PointBought extends AbstractEvent {

    private Long id;
    private Integer point;
    private Long userId;

    // public PointBought(Point aggregate) {
    //     super(aggregate);
    // }

    public PointBought() {
        super();
    }

    public boolean validate() {
        return this.id != null;
    }
}
//>>> DDD / Domain Event

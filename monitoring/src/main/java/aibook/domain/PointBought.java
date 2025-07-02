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
    private Integer totalPoint;
    private Long userId;
    private Integer boughtAmount;


    public PointBought() {
        super();
    }

    public boolean validate() {
        return this.id != null;
    }
}
//>>> DDD / Domain Event

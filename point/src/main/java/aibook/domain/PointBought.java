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

    public PointBought(Point point, int boughtAmount) {
        super(point);
        this.userId = point.getUserId();
        this.totalPoint = point.getPoint();
        this.boughtAmount = boughtAmount;
    }
}
//>>> DDD / Domain Event

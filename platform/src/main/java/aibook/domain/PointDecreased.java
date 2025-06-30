package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PointDecreased extends AbstractEvent {

    private Long id;
    private Long bookId;
    private Integer point;
    private Object userId;
}

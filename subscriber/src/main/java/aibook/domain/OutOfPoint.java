package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class OutOfPoint extends AbstractEvent {

    private Long id;
    private Integer point;
    private Long readingId;
    private Long userId;

    public OutOfPoint(Long id, Integer point, Long readingId, Long userId) {
        super();
        this.id = id;
        this.point = point;
        this.readingId = readingId;
        this.userId = userId;
    }
}
package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReadingApplied extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;
    private int usedPoint;

    public Long getReadingId() {
        return id;
    }

    public int getUsedPoint() {
        return usedPoint;
    }
    
}

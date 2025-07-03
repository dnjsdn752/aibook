package aibook.domain;

import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReadingApplied extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;
}

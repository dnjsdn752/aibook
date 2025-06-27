package aibook.domain;

import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReadingCanceled extends AbstractEvent {

    private Long id;
    private UserId userId;
    private BookId bookId;
    private Boolean isReading;
    private Date startReading;
    private String webURL;
}

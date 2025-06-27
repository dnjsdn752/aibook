package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReadingCanceled extends AbstractEvent {

    private Long id;
    private Object userId;
    private Object bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;
}

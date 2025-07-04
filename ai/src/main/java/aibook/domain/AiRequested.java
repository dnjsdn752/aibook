package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AiRequested extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String authorName;

    private String aiImage;
    private String aiSummary;
    private Long manuscriptId;

    public AiRequested(Object aggregate) {
        super(aggregate);
    }

    public AiRequested() {
        super();
    }
}

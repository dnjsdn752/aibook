package aibook.domain;

import aibook.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class AiRequested extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String authorName;

    public AiRequested(Manuscript aggregate) {
        super(aggregate);
    }

    public AiRequested() {
        super();
    }
}

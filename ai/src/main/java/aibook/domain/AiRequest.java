package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AiRequest extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String authorName;

    private String aiImage;
    private Stirng aiSummary;
}

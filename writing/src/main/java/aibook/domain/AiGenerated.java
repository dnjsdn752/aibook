package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AiGenerated extends AbstractEvent {

    private Long id;
    private Long manuscriptId;
    private String aiImage;
    private String aiSummary;
    private String title;
    private Long authorId;
    private String content;
}

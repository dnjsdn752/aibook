package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PublishingRequested extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private Object status;
    private String authorName;
    private String category;
    private Date date;
    private String aiImage;
    private String aiSummary;
}

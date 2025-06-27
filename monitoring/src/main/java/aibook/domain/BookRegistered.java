package aibook.domain;

import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BookRegistered extends AbstractEvent {

    private Long id;
    private String bookName;
    private String category;
    private String isBestSeller;
    private String authorName;
    private Long authorId;
    private String ai_image;
    private String ai_summary;
    private String bookContent;
    private Integer view;
    private Date date;
}

package aibook.domain;

import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BookRegistered extends AbstractEvent {

    
    private Long id;
    private String title;
    private String category;
    private Boolean isBestSeller;
    private String authorName;
    private Long authorId;
    private String aiImage;
    private String aiSummary;
    private String content;
    private Integer view;
    private Date date;

    // public boolean validate() {
    //     return this.id != null;
    // }
}

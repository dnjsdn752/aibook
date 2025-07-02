package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BadgeGranted extends AbstractEvent {

    private Long id;
    private String category;
    private Long authorId;
    private Boolean isBestSeller;
    private String authorName;
    private Integer view;
    private String title;


    public BadgeGranted() {
        super();
    }
}


//>>> DDD / Domain Event

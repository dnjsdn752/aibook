package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookRegistered extends AbstractEvent {

    private Long id;
    private String bookName;
    private String category;
    private String isBestSeller;
    private String authorName;
    private Long authorId;
    private String aiImage;
    private String aiSummary;
    private String bookContent;
    private Integer view;
    private Date date;

    public BookRegistered(Book aggregate) {
        super(aggregate);
    }

    public BookRegistered() {
        super();
    }
}
//>>> DDD / Domain Event

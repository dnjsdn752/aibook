package aibook.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "Bookpage_table")
@Data
public class Bookpage {

    @Id
    private Long id;
    private Long authorId;
    private String title;
    private String category;
    private Boolean isBestSeller;
    private String authorName;
    @Column(length = 1000)
    private String aiImage;
    @Column(length = 1000)
    private String aiSummary;
    @Column(length = 1000)
    private String content;
    private Integer view;
    private Date date;
}

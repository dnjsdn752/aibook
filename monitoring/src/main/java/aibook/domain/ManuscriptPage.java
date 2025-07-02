package aibook.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ManuscriptPage_table")
@Data
public class ManuscriptPage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String title;
    @Column(length = 1000)
    private String content;
    private Boolean status;
    private String authorName;
    private Date date;
    @Column(length = 1000)
    private String aiImage;
    @Column(length = 1000)
    private String aiSummary;
}

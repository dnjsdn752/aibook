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
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String authorName;
    private String bookName;
    private Integer view;
    private String aiImage;
    private String isBestseller;
}

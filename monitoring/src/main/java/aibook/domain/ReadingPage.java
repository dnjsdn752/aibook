package aibook.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ReadingPage_table")
@Data
public class ReadingPage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer userId;
    private Integer bookId;
    private Date startReading;
    private String webUrl;

    
}

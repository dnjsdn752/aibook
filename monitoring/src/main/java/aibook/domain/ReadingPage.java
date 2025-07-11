package aibook.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ReadingPage_table")
@Data
public class ReadingPage {

    @Id
    private Long id;

    private Long userId;
    private Long bookId;
    private Boolean isReading;
    private Date startReading;
    private String webUrl;
}

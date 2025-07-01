package aibook.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "Users_table")
@Data
public class UserPage {

    @Id
    private Long id;
    private String email;
    private String userName;
    private String password;
    private Boolean isSubscription;
    
}

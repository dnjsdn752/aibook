package aibook.domain;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PointView_table")
@Data
public class PointView {

    @Id
    private Long id;
    private int point;
    private Long userId;
    private Boolean isSubscription;
}

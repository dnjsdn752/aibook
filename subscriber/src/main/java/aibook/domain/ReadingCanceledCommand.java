package aibook.domain;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReadingCanceledCommand {
    private Long id;
    private Long userId;
    private Long bookId;
}

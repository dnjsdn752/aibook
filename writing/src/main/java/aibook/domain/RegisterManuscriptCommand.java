package aibook.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterManuscriptCommand {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String authorName;
}

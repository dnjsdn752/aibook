package aibook.domain;

import lombok.Data;

@Data
public class RequestAuthorCommand {
    private String email;
    private String authorName;
    private String introduction;
    private String featuredWorks;
}

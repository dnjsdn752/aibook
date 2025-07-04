package aibook.infra;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String userName;
    private String message;
    private Boolean isAuthor;
    private String authorName;

    public LoginResponse(Long id, String userName, String message, Boolean isAuthor, String authorName) {
        this.id = id;
        this.userName = userName;
        this.message = message;
        this.isAuthor = isAuthor;
        this.authorName = authorName;
    }
}

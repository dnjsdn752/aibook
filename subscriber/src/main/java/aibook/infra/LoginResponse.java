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

    public LoginResponse(Long id, String userName, String message, Boolean isAuthor) {
        this.id = id;
        this.userName = userName;
        this.message = message;
        this.isAuthor = isAuthor;
    }
}

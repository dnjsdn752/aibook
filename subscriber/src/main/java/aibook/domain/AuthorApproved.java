package aibook.domain;

import aibook.infra.AbstractEvent;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;

// DDD / Domain Event
@Data
@ToString
@NoArgsConstructor
public class AuthorApproved extends AbstractEvent {

    private Long id;           // 승인된 Author ID
    private Boolean isApprove; // 승인 여부
    private String email;      // ⭐️ 작가 이메일
    private String authorName;

    public AuthorApproved(Long id, Boolean isApprove, String email, String authorName) {
        super();
        this.id = id;
        this.isApprove = isApprove;
        this.email = email;
        this.authorName = authorName;
    }
}

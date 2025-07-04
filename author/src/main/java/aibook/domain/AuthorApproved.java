package aibook.domain;

import aibook.infra.AbstractEvent;
import lombok.*;

// <<< DDD / Domain Event
@Data
@ToString
@NoArgsConstructor
public class AuthorApproved extends AbstractEvent {

    private Long id;             // 작가 ID
    private Boolean isApprove;   // 승인 여부
    private String email;        // ⭐️ 이메일 필드 추가
    private String authorName;

    public AuthorApproved(Author aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.isApprove = true;                  // 기본 true로 처리 (상황에 따라 다르게)
        this.email = aggregate.getEmail();      // 작가 Entity에서 이메일 가져오기
        this.authorName = aggregate.getAuthorName();
    }

    public String getAuthorName() {
        return authorName;
    }
}

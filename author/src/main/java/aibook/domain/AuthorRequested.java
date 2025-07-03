package aibook.domain;

import aibook.infra.AbstractEvent;
import aibook.domain.Author;

public class AuthorRequested extends AbstractEvent {
    private Long id;
    private String email;
    private String authorName;
    private String introduction;
    private String featuredWorks;
    private Boolean isApprove;

    public AuthorRequested(Author aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.email = aggregate.getEmail();
        this.authorName = aggregate.getAuthorName(); 
        this.authorName = aggregate.getIntroduction();
        this.featuredWorks = aggregate.getFeaturedWorks();
        this.isApprove = aggregate.getIsApprove();
    }

    // 필요시 필드 추가 가능
}


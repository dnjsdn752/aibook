package aibook.domain;

import aibook.infra.AbstractEvent;

public class AuthorRequested extends AbstractEvent {

    public AuthorRequested(Author aggregate) {
        super(aggregate);
    }

    // 필요시 필드 추가 가능
}


package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class UserRegistered extends AbstractEvent {

    private Long id;
    private String email;
    private String userName;
    private String password;
}

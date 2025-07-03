package aibook.domain;

import aibook.SubscriberApplication;
import aibook.domain.UserRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String email;

    private String userName;

    private String password;

    private Boolean isSubscription;

    private Boolean recommendedSubscription = false;

    // ⭐️ 작가 여부 필드
    private Boolean isAuthor = false; // 기본값 false

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(
            this.getId(),
            this.getEmail(),
            this.getUserName(),
            this.getPassword()
        );
        userRegistered.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = SubscriberApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    public void buySubscription(BuySubscriptionCommand command) {
        this.isSubscription = command.getIsSubscription();

        SubscriptionBought subscriptionBought = new SubscriptionBought(this);
        subscriptionBought.publishAfterCommit();
    }

    public static void guideFeeConversionSuggestion(ReadingFailed readingFailed) {
        Long userId = readingFailed.getId();

        repository().findById(userId).ifPresent(user -> {
            if (Boolean.TRUE.equals(user.getIsSubscription())) {
                return;
            }

            user.setRecommendedSubscription(true);
            repository().save(user);
        });
    }
}

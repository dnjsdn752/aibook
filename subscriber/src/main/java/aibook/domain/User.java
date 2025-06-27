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
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String userName;

    private String password;

    private Boolean isSubscription;

    private Boolean recommendedSubscription = false;

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.setId(this.getId());
        userRegistered.setEmail(this.getEmail());
        userRegistered.setUserName(this.getUserName());
        userRegistered.setPassword(this.getPassword());
        userRegistered.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = SubscriberApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void buySubscription(BuySubscriptionCommand command) {
        //implement business logic here:
        // 예: 실제 구독 상태 변경 로직 추가
        this.isSubscription = command.getIsSubscription();

        SubscriptionBought subscriptionBought = new SubscriptionBought(this);
        subscriptionBought.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void guideFeeConversionSuggestion(
        ReadingFailed readingFailed
    ) {
        //implement business logic here:

        Long userId = readingFailed.getId();

        repository().findById(userId).ifPresent(user -> {
            if (Boolean.TRUE.equals(user.getIsSubscription())) {
                return; // 이미 구독 중이면 무시
            }

            user.setRecommendedSubscription(true); // 구독 유도 표시

            // // (선택) 이벤트 발행
            // SubscriptionRecommended event = new SubscriptionRecommended(user);
            // event.publishAfterCommit();

            repository().save(user);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

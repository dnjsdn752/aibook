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

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = SubscriberApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void buySubscription(BuySubscriptionCommand buySubscriptionCommand) {
        //implement business logic here:

        SubscriptionBought subscriptionBought = new SubscriptionBought(this);
        subscriptionBought.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void guideFeeConversionSuggestion(
        ReadingFailed readingFailed
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        */

        /** Example 2:  finding and process
        
        // if readingFailed.userIdbookId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> readingMap = mapper.convertValue(readingFailed.getUserId(), Map.class);
        // Map<Long, Object> readingMap = mapper.convertValue(readingFailed.getBookId(), Map.class);

        repository().findById(readingFailed.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

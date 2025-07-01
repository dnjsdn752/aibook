package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UserPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private UserPageRepository userPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserRegistered_then_CREATE_1(
        @Payload UserRegistered userRegistered
    ) {
        try {
            if (!userRegistered.validate()) return;

            // view 객체 생성
            UserPage userPage = new UserPage();
            // view 객체에 이벤트의 Value 를 set 함
            userPage.setId(userRegistered.getId());
            userPage.setEmail(userRegistered.getEmail());
            userPage.setUserName(userRegistered.getUserName());
            userPage.setPassword(userRegistered.getPassword());
            userPage.setIsSubscription(userRegistered.getIsSubscription());
            // view 레파지 토리에 ssave
            userPageRepository.save(userPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
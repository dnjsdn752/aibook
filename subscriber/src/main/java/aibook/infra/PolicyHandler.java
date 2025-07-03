package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReadingRepository readingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // ✅ 1. 독서 실패 시 구독 추천
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadingFailed'"
    )
    public void wheneverReadingFailed_GuideFeeConversionSuggestion(
        @Payload ReadingFailed readingFailed
    ) {
        System.out.println(
            "\n\n##### listener GuideFeeConversionSuggestion : " +
            readingFailed +
            "\n\n"
        );
        User.guideFeeConversionSuggestion(readingFailed);
    }

    // ✅ 2. 포인트 부족 시 구독 실패
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfPoint'"
    )
    public void wheneverOutOfPoint_FailSubscription(
        @Payload OutOfPoint outOfPoint
    ) {
        System.out.println(
            "\n\n##### listener FailSubscription : " + outOfPoint + "\n\n"
        );
        Reading.failSubscription(outOfPoint);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AuthorApproved'"
    )
    public void wheneverAuthorApproved_UpdateUserAsAuthor(
        @Payload AuthorApproved authorApproved
    ) {
        System.out.println("##### listener UpdateUserAsAuthor : " + authorApproved);

        // 이메일로 User 찾기
        User user = userRepository.findByEmail(authorApproved.getEmail());

        if (user != null) {
            user.setIsAuthor(Boolean.TRUE.equals(authorApproved.getIsApprove()));
            userRepository.save(user);

            System.out.println("User with email=" + authorApproved.getEmail() + " is now author.");
        } else {
            System.err.println("No user found with email=" + authorApproved.getEmail());
        }
    }

}

package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserRegistered'"
    )
    public void wheneverUserRegistered_GainRegisterPoint(
        @Payload UserRegistered userRegistered
    ) {
        UserRegistered event = userRegistered;
        System.out.println(
            "\n\n##### listener GainRegisterPoint : " + userRegistered + "\n\n"
        );

        // Sample Logic //
        Point.gainRegisterPoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadingApplied'"
    )
    public void wheneverReadingApplied_DecreasePoint(
        @Payload ReadingApplied readingApplied
    ) {
        ReadingApplied event = readingApplied;
        System.out.println(
            "\n\n##### listener DecreasePoint : " + readingApplied + "\n\n"
        );

        // Sample Logic //
        Point.decreasePoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadingCanceled'"
    )
    public void wheneverReadingCanceled_IncreasePoint(
        @Payload ReadingCanceled readingCanceled
    ) {
        ReadingCanceled event = readingCanceled;
        System.out.println(
            "\n\n##### listener IncreasePoint : " + readingCanceled + "\n\n"
        );

        // REST Request Sample

        // pointService.getPoint(/** mapping value needed */);

        // Sample Logic //
        Point.increasePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor

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
    UserRepository userRepository;

    @Autowired
    ReadingRepository readingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadingFailed'"
    )
    public void wheneverReadingFailed_GuideFeeConversionSuggestion(
        @Payload ReadingFailed readingFailed
    ) {
        ReadingFailed event = readingFailed;
        System.out.println(
            "\n\n##### listener GuideFeeConversionSuggestion : " +
            readingFailed +
            "\n\n"
        );

        // Sample Logic //
        User.guideFeeConversionSuggestion(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfPoint'"
    )
    public void wheneverOutOfPoint_FailSubscription(
        @Payload OutOfPoint outOfPoint
    ) {
        OutOfPoint event = outOfPoint;
        System.out.println(
            "\n\n##### listener FailSubscription : " + outOfPoint + "\n\n"
        );

        // Sample Logic //
        Reading.failSubscription(event);
    }
}
//>>> Clean Arch / Inbound Adaptor

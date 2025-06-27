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
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishingRequested'"
    )
    public void wheneverPublishingRequested_RegisterBook(
        @Payload PublishingRequested publishingRequested
    ) {
        PublishingRequested event = publishingRequested;
        System.out.println(
            "\n\n##### listener RegisterBook : " + publishingRequested + "\n\n"
        );

        // Sample Logic //
        Book.registerBook(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadingCanceled'"
    )
    public void wheneverReadingCanceled_ViewCount(
        @Payload ReadingCanceled readingCanceled
    ) {
        ReadingCanceled event = readingCanceled;
        System.out.println(
            "\n\n##### listener ViewCount : " + readingCanceled + "\n\n"
        );

        // Comments //
        //대여 신청 수 확인 후 5개 이상이면 뱃지부여
        // 비즈니스 로직은 aggregate에서 작성 후 불러오기

        // Sample Logic //
        Book.viewCount(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointDecreased'"
    )
    public void wheneverPointDecreased_ViewCount(
        @Payload PointDecreased pointDecreased
    ) {
        PointDecreased event = pointDecreased;
        System.out.println(
            "\n\n##### listener ViewCount : " + pointDecreased + "\n\n"
        );

        // Comments //
        //대여 신청 수 확인 후 5개 이상이면 뱃지부여
        // 비즈니스 로직은 aggregate에서 작성 후 불러오기

        // Sample Logic //
        Book.viewCount(event);
    }
}
//>>> Clean Arch / Inbound Adaptor

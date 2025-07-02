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
public class ReadingPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReadingPageRepository readingPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReadingApplied_then_CREATE_1(
        @Payload ReadingApplied readingApplied
    ) {
        try {
            if (!readingApplied.validate()) return;

            // view 객체 생성
            ReadingPage readingPage = new ReadingPage();
            // view 객체에 이벤트의 Value 를 set 함
            readingPage.setId(readingApplied.getId());
            readingPage.setUserId(readingApplied.getUserId());
            readingPage.setBookId(readingApplied.getBookId());
            readingPage.setIsReading(readingApplied.getIsReading());
            readingPage.setStartReading(readingApplied.getStartReading());
            readingPage.setWebUrl(readingApplied.getWebUrl());
            // view 레파지 토리에 save
            readingPageRepository.save(readingPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReadingCanceled_then_DELETE_1(
        @Payload ReadingCanceled readingCanceled
    ) {
        try {
            if (!readingCanceled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            readingPageRepository.deleteById(readingCanceled.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReadingFailed_then_DELETE_2(
        @Payload ReadingFailed readingFailed
    ) {
        try {
            if (!readingFailed.validate()) return;
            readingPageRepository.deleteById(readingFailed.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}

package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReadingPageViewHandler {

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
            readingPage.setId(readingApplied.getId());

            // ✅ Long → Integer 변환 (.intValue())
            readingPage.setUserId(readingApplied.getUserId().intValue());
            readingPage.setBookId(readingApplied.getBookId().intValue());

            readingPage.setStartReading(readingApplied.getStartReading());

            // ✅ getter 이름 확인 (getWebURL())
            readingPage.setWebUrl(readingApplied.getWebURL());

            // 저장
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
}

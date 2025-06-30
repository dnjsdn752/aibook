package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void whenEventReceived(@Payload String messageJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(messageJson);

            String eventType = jsonNode.get("type").asText();

            if ("ReadingApplied".equals(eventType)) {
                ReadingApplied readingApplied = objectMapper.treeToValue(jsonNode, ReadingApplied.class);

                if (!readingApplied.validate()) return;

                ReadingPage readingPage = new ReadingPage();
                readingPage.setId(readingApplied.getId());
                readingPage.setUserId(readingApplied.getUserId().intValue());
                readingPage.setBookId(readingApplied.getBookId().intValue());
                readingPage.setStartReading(readingApplied.getStartReading());
                readingPage.setWebUrl(readingApplied.getWebUrl());

                readingPageRepository.save(readingPage);

                System.out.println("📩 ReadingApplied 이벤트 수신 및 저장 완료: " + readingApplied);
            }

        } catch (Exception e) {
            System.err.println("❌ 이벤트 처리 중 예외 발생:");
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

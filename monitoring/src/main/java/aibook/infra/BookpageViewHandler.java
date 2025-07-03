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
public class BookpageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private BookpageRepository bookpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookRegistered_then_CREATE_1(
        @Payload BookRegistered bookRegistered
    ) {
        try {
            if (!bookRegistered.validate()) return;

            // view 객체 생성
            Bookpage bookpage = new Bookpage();
            // view 객체에 이벤트의 Value 를 set 함
            bookpage.setId(bookRegistered.getId());
            bookpage.setTitle(bookRegistered.getTitle());
            bookpage.setCategory("None");
            bookpage.setIsBestSeller(bookRegistered.getIsBestSeller());
            bookpage.setAuthorName(bookRegistered.getAuthorName());
            bookpage.setContent(bookRegistered.getContent());
            bookpage.setAuthorId(bookRegistered.getAuthorId());
            bookpage.setView(bookRegistered.getView());
            bookpage.setAiImage(bookRegistered.getAiImage());
            bookpage.setAiSummary(bookRegistered.getAiSummary());
            bookpage.setDate(bookRegistered.getDate());

    
    

            // view 레파지 토리에 save
            bookpageRepository.save(bookpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBadgeGranted_then_UPDATE_1(
        @Payload BadgeGranted badgeGranted
    ) {
        try {
            if (!badgeGranted.validate()) return;
            // view 객체 조회
            Optional<Bookpage> bookpageOptional = bookpageRepository.findById(
                badgeGranted.getId()
            );

            if (bookpageOptional.isPresent()) {
                Bookpage bookpage = bookpageOptional.get();
                bookpage.setView(badgeGranted.getView());
                bookpage.setIsBestSeller(badgeGranted.getIsBestSeller());
                bookpageRepository.save(bookpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}

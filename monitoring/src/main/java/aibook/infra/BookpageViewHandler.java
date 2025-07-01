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
            bookpage.setAuthorName(bookRegistered.getAuthorName());
            bookpage.setBookName(bookRegistered.getBookName());
            bookpage.setView(bookRegistered.getView());
            bookpage.setAiImage(bookRegistered.getAiImage());
            bookpage.setIsBestseller(bookRegistered.getIsBestSeller());
            // view 레파지 토리에 save
            bookpageRepository.save(bookpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookRegistered_then_UPDATE_1(
        @Payload BookRegistered bookRegistered
    ) {
        try {
            if (!bookRegistered.validate()) return;
            // view 객체 조회
            Optional<Bookpage> bookpageOptional = bookpageRepository.findById(
                bookRegistered.getId()
            );

            if (bookpageOptional.isPresent()) {
                Bookpage bookpage = bookpageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                bookpageRepository.save(bookpage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}

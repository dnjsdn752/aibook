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
public class ManuscriptPageViewHandler {

    //원고 등록
    @Autowired
    private ManuscriptPageRepository manuscriptPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenManuscriptRegistered_then_CREATE_1(
        @Payload ManuscriptRegistered manuscriptRegistered
    ) {
        try {
            if (!manuscriptRegistered.validate()) return;
            // view 객체 생성
            ManuscriptPage manuscriptPage = new ManuscriptPage();
            // view 객체에 이벤트의 Value 를 set 함
            manuscriptPage.setId(manuscriptRegistered.getId());
            manuscriptPage.setAuthorId(manuscriptRegistered.getAuthorId());
            manuscriptPage.setTitle(manuscriptRegistered.getTitle());
            manuscriptPage.setContent(manuscriptRegistered.getContent());
            manuscriptPage.setAuthorName(manuscriptRegistered.getAuthorName());
            manuscriptPage.setStatus(false);
            // view 레파지 토리에 save
            manuscriptPageRepository.save(bookpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //원고 수정
    @StreamListener(KafkaProcessor.INPUT)
    public void whenManuscriptEdited_then_UPDATE_1(
        @Payload ManuscriptEdited manuscriptEdited
    ) {
        try {
            if (!badgeGranted.validate()) return;
            // view 객체 조회
            Optional<ManuscriptPage> manuscriptPageOptional = manuscriptPageRepository.findById(
                manuscriptEdited.getId()
            );

            if (manuscriptPageOptional.isPresent()) {
                ManuscriptPage manuscriptPage = manuscriptPageOptional.get();
                manuscriptPage.setTitle(manuscriptEdited.getTitle());
                manuscriptPage.setContent(manuscriptEdited.getContent());
                manuscriptPageRepository.save(manuscriptPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //출간 요청
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPublishingRequested_then_UPDATE_1(
        @Payload PublishingRequested publishingRequested
    ) {
        try {
            if (!badgeGranted.validate()) return;
            // view 객체 조회
            Optional<ManuscriptPage> manuscriptPageOptional = manuscriptPageRepository.findById(
                publishingRequested.getId()
            );

            if (manuscriptPageOptional.isPresent()) {
                ManuscriptPage manuscriptPage = manuscriptPageOptional.get();
                manuscriptPage.setStatus(true);
                manuscriptPageRepository.save(manuscriptPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //ai 생성
    @StreamListener(KafkaProcessor.INPUT)
    public void whenAiGenerated_then_UPDATE_1(
        @Payload AiGenerated aiGenerated
    ) {
        try {
            if (!aiGenerated.validate()) return;
            // view 객체 조회
            Optional<ManuscriptPage> manuscriptPageOptional = manuscriptPageRepository.findById(
                aiGenerated.getManuscriptId()
            );

            if (manuscriptPageOptional.isPresent()) {
                ManuscriptPage manuscriptPage = manuscriptPageOptional.get();
                manuscriptPage.setAiImage(aiGenerated.getAiImage());
                manuscriptPage.setAiSummary(aiGenerated.getAiSummary());
                manuscriptPageRepository.save(manuscriptPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

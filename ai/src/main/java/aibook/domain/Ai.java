package aibook.domain;

import aibook.AiApplication;
import aibook.domain.AiGenerated;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Ai_table")
@Data
//<<< DDD / Aggregate Root
public class Ai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long manuscriptId;

    private String aiImage;

    private String aiSummary;

    private String title;

    private Long authorId;

    private String content;

    public static AiRepository repository() {
        AiRepository aiRepository = AiApplication.applicationContext.getBean(
            AiRepository.class
        );
        return aiRepository;
    }

    //<<< Clean Arch / Port Method
    public static void aiGenerate(AiRequested aiRequest) {
        //implement business logic here:

        
        Ai ai = new Ai();
        ai.setManuscriptId(aiRequest.getManuscriptId());
        ai.setAiImage(aiRequest.getAiImage());
        ai.setAiSummary(aiRequest.getAiSummary());
        ai.setTitle(aiRequest.getTitle());
        ai.setAuthorId(aiRequest.getAuthorId());
        ai.setContent(aiRequest.getContent());


        repository().save(ai);

        AiGenerated aiGenerated = new AiGenerated(ai);
        aiGenerated.publishAfterCommit();
        


    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

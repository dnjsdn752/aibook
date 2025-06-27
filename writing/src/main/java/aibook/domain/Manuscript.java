package aibook.domain;

import aibook.WritingApplication;
import aibook.domain.AiRequest;
import aibook.domain.PublishingRequested;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Manuscript_table")
@Data
//<<< DDD / Aggregate Root
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long authorId;

    private String title;

    private String content;

    private Status status;

    private String authorName;

    private Date date;

    private String aiImage;

    private String aiSummary;

    @PostPersist
    public void onPostPersist() {
        AiRequest aiRequest = new AiRequest(this);
        aiRequest.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        PublishingRequested publishingRequested = new PublishingRequested(this);
        publishingRequested.publishAfterCommit();
    }

    public static ManuscriptRepository repository() {
        ManuscriptRepository manuscriptRepository = WritingApplication.applicationContext.getBean(
            ManuscriptRepository.class
        );
        return manuscriptRepository;
    }

    //<<< Clean Arch / Port Method
    public void registerManuscript(
        RegisterManuscriptCommand registerManuscriptCommand
    ) {
        //implement business logic here:

        ManuscriptRegistered manuscriptRegistered = new ManuscriptRegistered(
            this
        );
        manuscriptRegistered.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void editManuscript(EditManuscriptCommand editManuscriptCommand) {
        //implement business logic here:

        ManuscriptEdited manuscriptEdited = new ManuscriptEdited(this);
        manuscriptEdited.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void aiImage(AiGenerated aiGenerated) {
        //implement business logic here:

        /** Example 1:  new item 
        Manuscript manuscript = new Manuscript();
        repository().save(manuscript);

        */

        /** Example 2:  finding and process
        
        // if aiGenerated.llmId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> aiMap = mapper.convertValue(aiGenerated.getLlmId(), Map.class);

        repository().findById(aiGenerated.get???()).ifPresent(manuscript->{
            
            manuscript // do something
            repository().save(manuscript);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

package aibook.domain;

import aibook.WritingApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
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

    private Boolean status;

    private String authorName;

    private Date date;

    private String aiImage;

    private String aiSummary;

    @PostPersist
    public void onPostPersist() {
        // 원고 등록 이벤트 발행
        ManuscriptRegistered manuscriptRegistered = new ManuscriptRegistered(this);
        manuscriptRegistered.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        // 원고 수정 이벤트 발행
        ManuscriptEdited manuscriptEdited = new ManuscriptEdited(this);
        manuscriptEdited.publishAfterCommit();
    }

    public static ManuscriptRepository repository() {
        ManuscriptRepository manuscriptRepository = WritingApplication.applicationContext.getBean(
            ManuscriptRepository.class
        );
        return manuscriptRepository;
    }

    //<<< Clean Arch / Port Method
    public void registerManuscript(RegisterManuscriptCommand command) {
        this.title = command.getTitle();
        this.content = command.getContent();
        this.status = false;
        this.authorName = command.getAuthorName();
        this.date = new Date();

        ManuscriptRegistered manuscriptRegistered = new ManuscriptRegistered(this);
        manuscriptRegistered.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void editManuscript(EditManuscriptCommand command) {
        this.title = command.getTitle();
        this.content = command.getContent();

        ManuscriptEdited manuscriptEdited = new ManuscriptEdited(this);
        manuscriptEdited.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void requestPublishing() {
        this.status = true;

        PublishingRequested publishingRequested = new PublishingRequested(this);
        publishingRequested.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void requestAi() {
        AiRequested aiRequested = new AiRequested(this);
        aiRequested.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void aiImage(AiGenerated aiGenerated) {
        repository().findById(aiGenerated.getManuscriptId()).ifPresent(manuscript -> {
            manuscript.setAiImage(aiGenerated.getAiImage());
            manuscript.setAiSummary(aiGenerated.getAiSummary());
            repository().save(manuscript);
        });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

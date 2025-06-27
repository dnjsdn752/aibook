package aibook.domain;

import aibook.SubscriberApplication;
import aibook.domain.ReadingApplied;
import aibook.domain.ReadingCanceled;
import aibook.domain.ReadingFailed;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reading_table")
@Data
//<<< DDD / Aggregate Root
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isReading;

    private Date startReading;

    private String webUrl;

    @Embedded
    private UserId userId;

    @Embedded
    private BookId bookId;

    @PostPersist
    public void onPostPersist() {
        ReadingApplied readingApplied = new ReadingApplied(this);
        readingApplied.publishAfterCommit();

        ReadingFailed readingFailed = new ReadingFailed(this);
        readingFailed.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        ReadingCanceled readingCanceled = new ReadingCanceled(this);
        readingCanceled.publishAfterCommit();
    }

    public static ReadingRepository repository() {
        ReadingRepository readingRepository = SubscriberApplication.applicationContext.getBean(
            ReadingRepository.class
        );
        return readingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void failSubscription(OutOfPoint outOfPoint) {
        //implement business logic here:

        /** Example 1:  new item 
        Reading reading = new Reading();
        repository().save(reading);

        ReadingFailed readingFailed = new ReadingFailed(reading);
        readingFailed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if outOfPoint.readingIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> pointMap = mapper.convertValue(outOfPoint.getReadingId(), Map.class);
        // Map<Long, Object> pointMap = mapper.convertValue(outOfPoint.getUserId(), Map.class);

        repository().findById(outOfPoint.get???()).ifPresent(reading->{
            
            reading // do something
            repository().save(reading);

            ReadingFailed readingFailed = new ReadingFailed(reading);
            readingFailed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

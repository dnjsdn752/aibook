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

    private Long userId;

    private Long bookId;

    public static ReadingRepository repository() {
        ReadingRepository readingRepository = SubscriberApplication.applicationContext.getBean(
            ReadingRepository.class
        );
        return readingRepository;
    }
    public void readingApplied(ReadingAppliedCommand command){
        this.isReading = false;
        this.startReading = new Date();
        this.webUrl = null;
        this.userId = command.getUserId();
        this.bookId = command.getBookId();

        // 1. 먼저 save() 해서 id 생성
        repository().save(this);

        // 2. 이벤트 생성
        ReadingApplied readingApplied = new ReadingApplied(this);
        readingApplied.publishAfterCommit();
    }

    public static void readingCanceled(ReadingCanceledCommand command){
        Reading reading = repository().findById(command.getId()).orElseThrow(
            () -> new EntityNotFoundException("해당 ID의 Reading이 존재하지 않습니다: "));
        
        ReadingCanceled readingApplied = new ReadingCanceled(reading);
        repository().delete(reading);
        readingApplied.publishAfterCommit();

        
    }
    
    public static void failSubscription(OutOfPoint outOfPoint) {
        Reading reading = repository().findById(outOfPoint.getReadingId()).orElseThrow(
            () -> new EntityNotFoundException("해당 ID의 Reading이 존재하지 않습니다: "));

        ReadingFailed readingFailed = new ReadingFailed(reading);
        readingFailed.publishAfterCommit();

        repository().delete(reading);
    }

    public static List<Reading> myReadings(Long id) {
        return repository().findByUserId(id);
    }
}
    
package aibook.domain;

import aibook.PointApplication;
import aibook.domain.OutOfPoint;
import aibook.domain.PointBought;
import aibook.domain.PointDecreased;
import aibook.domain.RegisterPointGained;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Point_table")
@Data
//<<< DDD / Aggregate Root
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer point;

    private Boolean isSubscribe;

    @Embedded
    private ReadingId readingId;

    @Embedded
    private UserId userId;

    public static PointRepository repository() {
        PointRepository pointRepository = PointApplication.applicationContext.getBean(
            PointRepository.class
        );
        return pointRepository;
    }

    //<<< Clean Arch / Port Method
    public static void gainRegisterPoint(UserRegistered userRegistered) {
        //implement business logic here:

        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        RegisterPointGained registerPointGained = new RegisterPointGained(point);
        registerPointGained.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(userRegistered.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);

            RegisterPointGained registerPointGained = new RegisterPointGained(point);
            registerPointGained.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void decreasePoint(ReadingApplied readingApplied) {
        //implement business logic here:

        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        PointDecreased pointDecreased = new PointDecreased(point);
        pointDecreased.publishAfterCommit();
        OutOfPoint outOfPoint = new OutOfPoint(point);
        outOfPoint.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if readingApplied.userIdbookId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> readingMap = mapper.convertValue(readingApplied.getUserId(), Map.class);
        // Map<Long, Object> readingMap = mapper.convertValue(readingApplied.getBookId(), Map.class);

        repository().findById(readingApplied.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);

            PointDecreased pointDecreased = new PointDecreased(point);
            pointDecreased.publishAfterCommit();
            OutOfPoint outOfPoint = new OutOfPoint(point);
            outOfPoint.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increasePoint(ReadingCanceled readingCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        */

        /** Example 2:  finding and process
        
        // if readingCanceled.userIdbookId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> readingMap = mapper.convertValue(readingCanceled.getUserId(), Map.class);
        // Map<Long, Object> readingMap = mapper.convertValue(readingCanceled.getBookId(), Map.class);

        repository().findById(readingCanceled.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

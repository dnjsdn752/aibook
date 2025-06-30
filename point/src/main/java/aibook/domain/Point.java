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

    private Long readingId;

    private Long userId;

    public static PointRepository repository() {
        PointRepository pointRepository = PointApplication.applicationContext.getBean(
            PointRepository.class
        );
        return pointRepository;
    }

    //<<< Clean Arch / Port Method
    public static void gainRegisterPoint(UserRegistered userRegistered) {
        
        Point point = new Point();
        point.setUserId(userRegistered.getUserId());
        point.setPoint(1000);
        point.setIsSubscribe(false);
        repository().save(point);

        RegisterPointGained registerPointGained = new RegisterPointGained(point);
        registerPointGained.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void decreasePoint(ReadingApplied readingApplied) {
        repository().findByUserId(readingApplied.getUserId())
        .ifPresent(point -> {
            int usedPoint = readingApplied.getUsedPoint();
            if (point.getPoint() >= usedPoint) {
                point.setPoint(point.getPoint() - usedPoint);
                point.setReadingId(readingApplied.getReadingId());
                repository().save(point);

                PointDecreased pointDecreased = new PointDecreased(point);
                pointDecreased.publishAfterCommit();
            } else {
                OutOfPoint outOfPoint = new OutOfPoint(point);
                outOfPoint.publishAfterCommit();
            }
        });
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increasePoint(ReadingCanceled readingCanceled) {
        repository().findByUserId(readingCanceled.getUserId())
        .ifPresent(point -> {
            int refundPoint = readingCanceled.getRefundPoint();
            point.setPoint(point.getPoint() + refundPoint);
            repository().save(point);
        });
    }
    //>>> Clean Arch / Port Method

    public static Point buyPoint(PointCharged pointCharged) {
        return repository().findByUserId(pointCharged.getUserId())
            .map(point -> {
                point.setPoint(point.getPoint() + pointCharged.getAmount());
                repository().save(point);

                PointBought event = new PointBought(point, pointCharged.getAmount());
                event.publishAfterCommit();

                return point;
            })
            .orElseGet(() -> {
                Point point = new Point();
                point.setUserId(pointCharged.getUserId());
                point.setPoint(pointCharged.getAmount());
                point.setIsSubscribe(false);
                repository().save(point);

                PointBought event = new PointBought(point, pointCharged.getAmount());
                event.publishAfterCommit();

                return point;
            });
}



}
//>>> DDD / Aggregate Root

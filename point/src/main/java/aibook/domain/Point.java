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
        point.setUserId(userRegistered.getId());
        point.setPoint(1000);
        point.setIsSubscribe(false);
        repository().save(point);

        RegisterPointGained registerPointGained = new RegisterPointGained(point);
        registerPointGained.publishAfterCommit();

    }

    public static Point decreasePoint(ReadingApplied readingApplied) {
        return repository().findByUserId(readingApplied.getUserId())
            .map(point -> {
                int usedPoint = readingApplied.getUsedPoint();
                if(point.getIsSubscribe()){
                    PointDecreased pointDecreased = new PointDecreased(point);
                    pointDecreased.setBookId(readingApplied.getBookId());
                    pointDecreased.publishAfterCommit();
                    return point;
                }else{
                    if (point.getPoint() >= usedPoint) {
                        point.setPoint(point.getPoint() - readingApplied.getUsedPoint());
                        point.setReadingId(readingApplied.getReadingId());
                        repository().save(point);

                        PointDecreased pointDecreased = new PointDecreased(point);
                        pointDecreased.setBookId(readingApplied.getBookId());
                        pointDecreased.publishAfterCommit();

                        return point;
                    } else {
                        OutOfPoint outOfPoint = new OutOfPoint(
                            point.getId(),
                            point.getPoint(),
                            readingApplied.getReadingId(), // 여기서 Reading의 ID
                            readingApplied.getUserId()
                        );
                        outOfPoint.publishAfterCommit();
                        return point;
                    }
                }   
            })
            .orElse(null);
    }


    public static Point increasePoint(ReadingCanceled readingCanceled) {
        return repository().findByUserId(readingCanceled.getUserId())
            .map(point -> {
                if(!point.getIsSubscribe()){
                int refundPoint = readingCanceled.getRefundPoint();
                point.setPoint(point.getPoint() + refundPoint);
                repository().save(point);
                }
                return point;

            })
            .orElse(null);
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
    public static Point update_Subscrib(UpdateSubscription updateSubscription){
        return repository().findByUserId(updateSubscription.getId())
            .map(point -> {
                point.setIsSubscribe(updateSubscription.getIsSubscription());
                SubscriptionUpdate event = new SubscriptionUpdate(point);
                event.publishAfterCommit();

                return point;
            })
            .orElse(null);
    }
}



//>>> DDD / Aggregate Root

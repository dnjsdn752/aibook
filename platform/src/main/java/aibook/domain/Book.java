package aibook.domain;

import aibook.PlatformApplication;
import aibook.domain.BadgeGranted;
import aibook.domain.BookRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long authorId;

    private String bookName;

    private String category;

    private String isBestSeller;

    private String authorName;

    private String aiImage;

    private String aiSummary;

    private String bookContent;

    private Integer view;

    private Date date;

    public static BookRepository repository() {
        BookRepository bookRepository = PlatformApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    //<<< Clean Arch / Port Method
    public static void registerBook(PublishingRequested publishingRequested) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookRegistered bookRegistered = new BookRegistered(book);
        bookRegistered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(publishingRequested.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookRegistered bookRegistered = new BookRegistered(book);
            bookRegistered.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void viewCount(ReadingCanceled readingCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BadgeGranted badgeGranted = new BadgeGranted(book);
        badgeGranted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if readingCanceled.userIdbookId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> readingMap = mapper.convertValue(readingCanceled.getUserId(), Map.class);
        // Map<Long, Object> readingMap = mapper.convertValue(readingCanceled.getBookId(), Map.class);

        repository().findById(readingCanceled.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BadgeGranted badgeGranted = new BadgeGranted(book);
            badgeGranted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void viewCount(PointDecreased pointDecreased) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BadgeGranted badgeGranted = new BadgeGranted(book);
        badgeGranted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if pointDecreased.readingIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> pointMap = mapper.convertValue(pointDecreased.getReadingId(), Map.class);
        // Map<Long, Object> pointMap = mapper.convertValue(pointDecreased.getUserId(), Map.class);

        repository().findById(pointDecreased.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BadgeGranted badgeGranted = new BadgeGranted(book);
            badgeGranted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

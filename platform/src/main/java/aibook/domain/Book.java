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

    private String title;

    private String category;

    private Boolean isBestSeller;

    private String authorName;
    
    @Column(length = 1000)
    private String aiImage;
    @Column(length = 1000)
    private String aiSummary;
    
    @Column(length = 1000)
    private String content;

    private Integer view;

    private Date date;

    public static BookRepository repository() {
        BookRepository bookRepository = PlatformApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    //도서 등록
    public static void registerBook(PublishingRequested event) {
        Book book = new Book();
        book.setAuthorId(event.getAuthorId());
        book.setTitle(event.getTitle());
        book.setContent(event.getContent());
        book.setAuthorName(event.getAuthorName());
        book.setDate(new Date());
        book.setAiImage(event.getAiImage());
        book.setAiSummary(event.getAiSummary());
        book.setCategory(event.getCategory());
        book.setView(0);
        book.setIsBestSeller(false);
        repository().save(book);

        BookRegistered bookRegistered = new BookRegistered(book);
        bookRegistered.publishAfterCommit();
    }

    
    public static void viewCount(ReadingCanceled readingCanceled) {
        Book book = repository().findById(readingCanceled.getBookId()).orElseThrow(
            () -> new EntityNotFoundException("해당 ID의 Book이 존재하지 않습니다: "));
        int view = book.getView();

        if(view > 0)
            book.setView(view-1);
        else
            book.setView(0);
        
        if(book.getView() >= 5)
            book.setIsBestSeller(true);
        else
            book.setIsBestSeller(false);

        BadgeGranted badgeGranted = new BadgeGranted(book);
        badgeGranted.publishAfterCommit();
    }

    public static void viewCount(PointDecreased pointDecreased) {
        Book book = repository().findById(pointDecreased.getBookId()).orElseThrow(
            () -> new EntityNotFoundException("해당 ID의 Book이 존재하지 않습니다: "));

        book.setView(book.getView() + 1);
        
        if(book.getView() >= 5)
            book.setIsBestSeller(true);
        else
            book.setIsBestSeller(false);

        BadgeGranted badgeGranted = new BadgeGranted(book);
        badgeGranted.publishAfterCommit();
    }

    

}

package aibook.domain;

import aibook.AuthorApplication;
import aibook.domain.AuthorRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Author_table")
@Data
//<<< DDD / Aggregate Root
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String authorName;

    private String introduction;

    private String featuredWorks;

    private Boolean isApprove;

    // @PostPersist
    // public void onPostPersist() {
    //     AuthorRegistered authorRegistered = new AuthorRegistered(this);
    //     authorRegistered.publishAfterCommit();
    // }

    public static AuthorRepository repository() {
        AuthorRepository authorRepository = AuthorApplication.applicationContext.getBean(
            AuthorRepository.class
        );
        return authorRepository;
    }

    public static Author requestAuthor(RequestAuthorCommand command) {
        Author author = new Author();
        author.setEmail(command.getEmail());
        author.setAuthorName(command.getAuthorName());
        author.setIntroduction(command.getIntroduction());
        author.setFeaturedWorks(command.getFeaturedWorks());
        author.setIsApprove(false); // 등록 요청은 승인 전 상태

        // ✅ 저장 후 ID 포함된 객체 반환
        Author savedAuthor = repository().save(author);

        // ✅ savedAuthor 기준으로 이벤트 생성
        AuthorRequested event = new AuthorRequested(savedAuthor);
        event.publishAfterCommit();

        return savedAuthor;
    }


    //<<< Clean Arch / Port Method
    public void approveAuthor() {
        //implement business logic here:
        this.setIsApprove(true);

        AuthorApproved authorApproved = new AuthorApproved(this);
        authorApproved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void disapproveAuthor() {
        //implement business logic here:
        this.setIsApprove(false);
        
        AuthorDisApproved authorDisApproved = new AuthorDisApproved(this);
        authorDisApproved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
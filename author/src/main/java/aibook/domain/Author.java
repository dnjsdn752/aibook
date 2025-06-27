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

    @PostPersist
    public void onPostPersist() {
        AuthorRegistered authorRegistered = new AuthorRegistered(this);
        authorRegistered.publishAfterCommit();
    }

    public static AuthorRepository repository() {
        AuthorRepository authorRepository = AuthorApplication.applicationContext.getBean(
            AuthorRepository.class
        );
        return authorRepository;
    }

    //<<< Clean Arch / Port Method
    public void approveAuthor(ApproveAuthorCommand approveAuthorCommand) {
        //implement business logic here:

        AuthorApproved authorApproved = new AuthorApproved(this);
        authorApproved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void disapproveAuthor(
        DisapproveAuthorCommand disapproveAuthorCommand
    ) {
        //implement business logic here:

        AuthorDisApproved authorDisApproved = new AuthorDisApproved(this);
        authorDisApproved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

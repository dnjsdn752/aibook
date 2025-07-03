package aibook.infra;

import aibook.domain.*;
import java.util.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/authors")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    // @GetMapping("/authors")
    // public List<Author> getAllAuthorRequests() {
    //     Iterable<Author> iterable = authorRepository.findAll();
    //     List<Author> list = new ArrayList<>();
    //     iterable.forEach(list::add);
    //     return list;
    // }

    @PostMapping("/authors/request")
    public Author requestAuthor(@RequestBody RequestAuthorCommand command) {
        return Author.requestAuthor(command);
    }

    @RequestMapping(
        value = "/authors/{id}/approveauthor",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author approveAuthor(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /author/approveAuthor  called #####");
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.approveAuthor();

        authorRepository.save(author);
        return author;
    }

    @RequestMapping(
        value = "/authors/{id}/disapproveauthor",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author disapproveAuthor(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /author/disapproveAuthor  called #####");
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.disapproveAuthor();

        authorRepository.save(author);
        return author;
    }
}
//>>> Clean Arch / Inbound Adaptor
package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/authors/request")
    public Author requestAuthor(@RequestBody RequestAuthorCommand command) {
        return Author.requestAuthor(command);
    }

    @PutMapping("/authors/{id}/approveauthor")
    public Author approveAuthor(@PathVariable Long id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.approveAuthor();
        authorRepository.save(author);
        return author;
    }

    @PutMapping("/authors/{id}/disapproveauthor")
    public Author disapproveAuthor(@PathVariable Long id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.disapproveAuthor();
        authorRepository.save(author);
        return author;
    }

    // ✅ 저자 ID로 단건 조회
    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) throws Exception {
        return authorRepository.findById(id)
            .orElseThrow(() -> new Exception("Author not found"));
    }
}

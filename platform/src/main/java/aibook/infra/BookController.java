package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // ✅ 책 ID로 단건 조회
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) throws Exception {
        return bookRepository.findById(id)
            .orElseThrow(() -> new Exception("Book not found"));
    }
}

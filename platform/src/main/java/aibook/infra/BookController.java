package aibook.infra;

import aibook.domain.*;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/books")
@Transactional
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(
        value = "/mybooks",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Iterable<Book> mybooks(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody List<Long> ids
    ) throws Exception {
        System.out.println(
            "##### /books/mybooks  called #####"
        );
        return bookRepository.findAllById(ids);
    }


}
//>>> Clean Arch / Inbound Adaptor

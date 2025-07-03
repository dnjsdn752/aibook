package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/manuscripts")
@Transactional
public class ManuscriptController {

    @Autowired
    ManuscriptRepository manuscriptRepository;

    @RequestMapping(
        value = "/manuscripts/registermanuscript",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript registerManuscript(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterManuscriptCommand registerManuscriptCommand
    ) throws Exception {
        System.out.println(
            "##### /manuscript/registerManuscript  called #####"
        );
        Manuscript manuscript = new Manuscript();
        manuscriptRepository.save(manuscript);
        manuscript.registerManuscript(registerManuscriptCommand);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/{id}/editmanuscript",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript editManuscript(
        @PathVariable(value = "id") Long id,
        @RequestBody EditManuscriptCommand editManuscriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /manuscript/editManuscript  called #####");
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(
            id
        );

        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        Manuscript manuscript = optionalManuscript.get();
        manuscript.editManuscript(editManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/{id}/requestai",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript requestAi(
        @PathVariable(value = "id") Long id
    ) throws Exception {
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(id);
        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));

        Manuscript manuscript = optionalManuscript.get();
        manuscript.requestAi();

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/{id}/requestpublishing",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript requestPublishing(
        @PathVariable(value = "id") Long id
    ) throws Exception {
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(id);
        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));

        Manuscript manuscript = optionalManuscript.get();
        manuscript.requestPublishing();

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @GetMapping("/manuscripts/author/{authorId}")
    public List<Manuscript> getByAuthorId(@PathVariable Long authorId) {
        return manuscriptRepository.findByAuthorIdAndStatusFalse(authorId);
    }

}
//>>> Clean Arch / Inbound Adaptor

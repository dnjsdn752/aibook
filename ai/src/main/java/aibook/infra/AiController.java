package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/ais")
@Transactional
public class AiController {

    @Autowired
    AiRepository aiRepository;

    @Autowired
    LlmService llmService;

    @PostMapping("/ais/{id}/generated")
    public ResponseEntity<?> generateAi(@PathVariable Long id) {
        Ai ai = aiRepository.findById(id).orElseThrow();

        AiRequest request = new AiRequest();
        request.setId(ai.getManuscriptId());
        request.setTitle(ai.getTitle());
        request.setContent(ai.getContent());
        request.setAuthorId(ai.getAuthorId());
        request.setAuthorName("작성자");

        llmService.callLlmAndSave(request);

        return ResponseEntity.ok("LLM 요청 완료");
    }
}
//>>> Clean Arch / Inbound Adaptor

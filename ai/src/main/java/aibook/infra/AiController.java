package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@Transactional
public class AiController {

    @Autowired
    AiRepository aiRepository;

    @Autowired
    LlmService llmService;

    // ✅ AI 생성 요청
    @PostMapping("/ais/{id}/generated")
    public ResponseEntity<?> generateAi(@PathVariable Long id) {
        Ai ai = aiRepository.findById(id).orElseThrow();

        AiRequested request = new AiRequested();
        request.setId(ai.getManuscriptId());
        request.setTitle(ai.getTitle());
        request.setContent(ai.getContent());
        request.setAuthorId(ai.getAuthorId());
        request.setAuthorName("작성자");

        llmService.callLlmAndSave(request);

        return ResponseEntity.ok("LLM 요청 완료");
    }

    // ✅ AI 결과 조회 (bookId → manuscriptId로 매핑될 경우)
    @GetMapping("/ais/book/{bookId}")
    public Ai getAiByBookId(@PathVariable Long bookId) throws Exception {
        Ai ai = aiRepository.findByManuscriptId(bookId);
        if (ai == null) throw new Exception("AI not found for bookId: " + bookId);
        return ai;
    }
}

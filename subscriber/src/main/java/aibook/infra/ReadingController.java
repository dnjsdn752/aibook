package aibook.infra;

import aibook.domain.*;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class ReadingController {

    @Autowired
    ReadingRepository readingRepository;

    // 🔵 대여 요청
    @PostMapping("/reading")
    public Reading readingApply(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody ReadingAppliedCommand readingAppliedCommand
    ) throws Exception {
        System.out.println("##### /reading/apply called #####");
        Reading reading = new Reading();
        readingRepository.save(reading);
        reading.readingApplied(readingAppliedCommand);
        return reading;
    }

    // 🔴 대여 취소
    @DeleteMapping("/reading")
    public void readingCancel(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody ReadingCanceledCommand readingCanceledCommand
    ) throws Exception {
        System.out.println("##### /reading/cancel called #####");
        Reading.readingCanceled(readingCanceledCommand);
    }

    // ✅ 유저별 대여 목록 조회
    @GetMapping("/readings/user/{userId}")
    public List<Reading> getReadingsByUser(@PathVariable Long userId) {
        return readingRepository.findByUserId(userId);
    }
}

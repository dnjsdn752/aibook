package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/points")
@Transactional
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @GetMapping("/{userId}")
    public Point getPointByUserId(@PathVariable Long userId) {
        return pointRepository.findByUserId(userId).orElse(null);
    }


    @PostMapping("/users/registered")
    public void userRegistered(@RequestBody UserRegistered event) {
        Point.gainRegisterPoint(event);
    }

    @PostMapping("/readings/applied")
    public Point readingApplied(@RequestBody ReadingApplied event) {
        return Point.decreasePoint(event);
    }

    @PostMapping("/readings/canceled")
    public Point readingCanceled(@RequestBody ReadingCanceled event) {
        return Point.increasePoint(event);
    }

    @PostMapping("/charged")
    public Point pointCharged(@RequestBody PointCharged event) {
        return Point.buyPoint(event);
    }

}
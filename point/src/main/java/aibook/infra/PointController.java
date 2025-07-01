package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/points")
@Transactional
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @GetMapping
    public Iterable<Point> getAllPoints() {
        return pointRepository.findAll();
    }


    // 포인트 id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<Point> getPointById(@PathVariable Long id) {
        return pointRepository.findById(id)
                .map(point -> ResponseEntity.ok(point))
                .orElse(ResponseEntity.notFound().build());
    }

    // userId로 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<Point> getPointByUserId(@PathVariable Long userId) {
        return pointRepository.findByUserId(userId)
                .map(point -> ResponseEntity.ok(point))
                .orElse(ResponseEntity.notFound().build());
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
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
// @RequestMapping(value="/points")
@Transactional
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @PostMapping("/users/registered")
    public void userRegistered(@RequestBody UserRegistered event) {
        Point.gainRegisterPoint(event);
    }

    @PostMapping("/readings/applied")
    public void readingApplied(@RequestBody ReadingApplied event) {
        Point.decreasePoint(event);
    }

    @PostMapping("/readings/canceled")
    public void readingCanceled(@RequestBody ReadingCanceled event) {
        Point.increasePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor

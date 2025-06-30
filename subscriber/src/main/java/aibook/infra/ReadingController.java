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
// @RequestMapping(value="/readings")
@Transactional
public class ReadingController {

    @Autowired
    ReadingRepository readingRepository;

    @RequestMapping(
        value = "/reading/apply",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Reading readingApply(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody ReadingAppliedCommand readingAppliedCommand
    ) throws Exception {
        System.out.println(
            "##### /reading/apply  called #####"
        );
        Reading reading = new Reading();
        readingRepository.save(reading);
        reading.readingApplied(readingAppliedCommand);
        return reading;
    }

    @RequestMapping(
        value = "/reading/apply",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public void readingCancel(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody ReadingCanceledCommand readingCanceledCommand
    ) throws Exception {
        System.out.println(
            "##### /reading/apply  called #####"
        );
        Reading.readingCanceled(readingCanceledCommand);
        
    }
}

    

package aibook.infra;

import aibook.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadingHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Reading>> {

    @Override
    public EntityModel<Reading> process(EntityModel<Reading> model) {
        return model;
    }
}

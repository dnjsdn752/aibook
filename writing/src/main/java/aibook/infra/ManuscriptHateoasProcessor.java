package aibook.infra;

import aibook.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ManuscriptHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Manuscript>> {

    @Override
    public EntityModel<Manuscript> process(EntityModel<Manuscript> model) {
        String baseUri = model.getRequiredLink("self").getHref();

        model.add(
            Link.of(baseUri + "/editmanuscript").withRel("editmanuscript")
        );
        model.add(
            Link.of(baseUri + "/requestpublishing").withRel("requestpublishing")
        );
        model.add(
            Link.of(baseUri + "/requestai").withRel("requestai")
        );

        return model;
    }
}

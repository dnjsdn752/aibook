package aibook.infra;

import aibook.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "readingPages",
    path = "readingPages"
)
public interface ReadingPageRepository
    extends PagingAndSortingRepository<ReadingPage, Long> {}

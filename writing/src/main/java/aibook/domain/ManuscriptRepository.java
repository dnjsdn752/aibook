package aibook.domain;

import aibook.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "manuscripts",
    path = "manuscripts"
)
public interface ManuscriptRepository
    extends PagingAndSortingRepository<Manuscript, Long> {
        List<Manuscript> findByAuthorIdAndStatusFalse(Long authorId);
    }

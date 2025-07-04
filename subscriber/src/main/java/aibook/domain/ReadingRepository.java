package aibook.domain;

import aibook.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;


//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "readings", path = "readings")
public interface ReadingRepository
    extends PagingAndSortingRepository<Reading, Long> {
        List<Reading> findByUserId(Long userId);
    }

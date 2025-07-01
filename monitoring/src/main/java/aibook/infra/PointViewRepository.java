package aibook.infra;

import aibook.domain.PointView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "points", path = "points")
public interface PointViewRepository extends CrudRepository<PointView, Long> {
    PointView findByUserId(Long userId);
}
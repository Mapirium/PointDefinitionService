package ch.mapirium.server.pointdefservice.repo;

import ch.mapirium.server.pointdefservice.model.PointDefinitionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository für den Zugriff auf die Punkt-Definitionen
 */
public interface PointDefinitionRepository extends CrudRepository<PointDefinitionEntity, Long> {

    @Query("SELECT p FROM PointDefinitionEntity p WHERE p.publicId = :publicId")
    PointDefinitionEntity findByPublicId(@Param("publicId") String publicId);

    @Query("SELECT p FROM PointDefinitionEntity p WHERE p.mapId = :mapId")
    List<PointDefinitionEntity> findByMapId(@Param("mapId") String mapId);
}

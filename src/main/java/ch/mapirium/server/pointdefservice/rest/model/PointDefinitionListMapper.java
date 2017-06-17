package ch.mapirium.server.pointdefservice.rest.model;

import ch.mapirium.server.pointdefservice.model.PointDefinitionEntity;
import ch.mapirium.server.pointdefservice.rest.controller.PointDefinitionRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mappt eine Liste von Punkt-Definitionen in eine Rest-Ressource
 */
@Component
public class PointDefinitionListMapper {
    
    @Autowired
    private PointDefinitionMapper pointDefinitionMapper;

    public PointDefinitionListResource fromEntity(Iterable<PointDefinitionEntity> entities, String mapId) {
        PointDefinitionListResource result = new PointDefinitionListResource();
        result.add(linkTo(methodOn(PointDefinitionRestController.class).getAll(mapId)).withSelfRel());
        
        // Die einzelnen Entitäten mappen
        List<PointDefinitionResource> listResource = StreamSupport.stream(entities.spliterator(), false).map(pointDefinitionMapper::fromEntity).collect(Collectors.toList());
        result.embed("pointDefinitions", listResource);

        return result;
    }
}

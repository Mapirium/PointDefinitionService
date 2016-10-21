package ch.mapirium.server.pointdefservice.rest.model;

import ch.mapirium.server.pointdefservice.model.PointDefinitionEntity;
import ch.mapirium.server.pointdefservice.rest.controller.PointDefinitionRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität einer Punkt-Definition
 */
@Component
public class PointDefinitionMapper {
    public PointDefinitionResource fromEntity(PointDefinitionEntity entity) {
        PointDefinitionResource result = new PointDefinitionResource();
        result.setPublicId(entity.getPublicId());
        result.setName(entity.getName());
        result.setMapId(entity.getMapId());
        result.setCreatedAt(entity.getCreatedAt());

        result.add(linkTo(methodOn(PointDefinitionRestController.class).getByPublicId(result.getMapId(), result.getPublicId())).withSelfRel());
        result.add(new Link("/map/" + entity.getMapId() + "/pointdefinition/" + entity.getPublicId() + "/fielddefinition", "fielddefinitions"));

        return result;
    }

    public PointDefinitionEntity toEntity(PointDefinitionResource resource) {
        PointDefinitionEntity result = new PointDefinitionEntity();
        result.setPublicId(resource.getPublicId());
        result.setName(resource.getName());
        result.setMapId(resource.getMapId());
        return result;
    }
}

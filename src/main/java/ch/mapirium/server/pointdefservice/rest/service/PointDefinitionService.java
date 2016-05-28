package ch.mapirium.server.pointdefservice.rest.service;

import ch.mapirium.server.pointdefservice.model.PointDefinitionEntity;
import ch.mapirium.server.pointdefservice.repo.PointDefinitionRepository;
import ch.mapirium.server.pointdefservice.rest.gateway.PublicIdGateway;
import ch.mapirium.server.pointdefservice.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Verwaltet die Punkt-Definitionen
 */
@Component
public class PointDefinitionService {

    @Autowired
    private PointDefinitionRepository pointDefinitionRepository;

    @Autowired
    private PublicIdGateway publicIdGateway;

    public PointDefinitionEntity createDefinition(PointDefinitionEntity newDefinition) {
        // Öffentliche ID lösen
        PublicIdResource publicId = publicIdGateway.createNewPublicId();
        newDefinition.setPublicId(publicId.getPublicId());

        // Definition speichern
        PointDefinitionEntity savedDefinition = pointDefinitionRepository.save(newDefinition);

        return savedDefinition;
    }
}

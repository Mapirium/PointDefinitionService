package ch.mapirium.server.pointdefservice.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.pointdefservice.model.PointDefinitionEntity;
import ch.mapirium.server.pointdefservice.repo.PointDefinitionRepository;
import ch.mapirium.server.pointdefservice.rest.model.PointDefinitionMapper;
import ch.mapirium.server.pointdefservice.rest.model.PointDefinitionResource;
import ch.mapirium.server.pointdefservice.rest.service.PointDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Schnittstelle für die Punkt-Definitionen
 */
@RestController
@RequestMapping(path = "/map/{mapId}/pointdefinition")
public class PointDefinitionRestController {

    @Autowired
    private PointDefinitionRepository pointDefinitionRepository;

    @Autowired
    private PointDefinitionMapper pointDefinitionMapper;

    @Autowired
    private PointDefinitionService pointDefinitionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PointDefinitionResource> getAll(@PathVariable("mapId") String mapId){
        // Daten laden
        List<PointDefinitionEntity> points = pointDefinitionRepository.findByMapId(mapId);

        // Mappen
        List<PointDefinitionResource> result = points.stream().map(pointDefinitionMapper::fromEntity).collect(Collectors.toList());
        return result;
    }

    @RequestMapping(path = "/{publicId}", method = RequestMethod.GET)
    public PointDefinitionResource getByPublicId(@PathVariable("mapId") String mapId, @PathVariable("publicId") String publicId) {
        // Definition suchen
        PointDefinitionEntity entity = pointDefinitionRepository.findByPublicId(publicId);

        // Wenn wir nichts gefunden haben, geben wir einen Fehler zurück
        if (entity == null) {
            throw new NotFoundException("Keine Punkt-Definition mit der ID " + publicId + " gefunden");
        } else {
            // Mappen
            PointDefinitionResource result = pointDefinitionMapper.fromEntity(entity);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public PointDefinitionResource create(@PathVariable("mapId") String mapId, @Validated @RequestBody PointDefinitionResource newDefinition){
        // Karten-ID übernehmen
        newDefinition.setMapId(mapId);

        // Entität erstellen
        PointDefinitionEntity entity = pointDefinitionMapper.toEntity(newDefinition);

        // Speichern
        PointDefinitionEntity savedDefinition = pointDefinitionService.createDefinition(entity);

        // Resource erstellen und zurückgeben
        PointDefinitionResource result = pointDefinitionMapper.fromEntity(savedDefinition);
        return result;
    }
}

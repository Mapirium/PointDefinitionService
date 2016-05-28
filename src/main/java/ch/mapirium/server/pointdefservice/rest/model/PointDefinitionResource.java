package ch.mapirium.server.pointdefservice.rest.model;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Definiert die REST-Ressource für die Punkt-Definition
 */
public class PointDefinitionResource extends ResourceSupport {

    private String publicId;

    private Date createdAt;

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 50)
    private String mapId;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
}

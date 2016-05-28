package ch.mapirium.server.pointdefservice.model;

import ch.mapirium.server.common.jpa.model.PublicIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Repräsentiert die Definition eines Kartenpunktes.
 */
@Entity
@Table(name = "pointdefinition")
public class PointDefinitionEntity extends PublicIdEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "map_id", nullable = false)
    private String mapId;

    public PointDefinitionEntity() {
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

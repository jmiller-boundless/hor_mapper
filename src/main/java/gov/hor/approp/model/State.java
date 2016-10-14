package gov.hor.approp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bedatadriven.jackson.datatype.jts.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "state", schema = "tiger")
public class State {
    @Id
    private String name;
    @Column(name = "the_geom", columnDefinition = "Geometry")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry the_geom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonSerialize(using = GeometrySerializer.class)
    public Geometry getThe_geom() {
        return the_geom;
    }

    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonProperty("geom")
    public void setThe_geom(Geometry the_geom) {
        this.the_geom = the_geom;
    }

}

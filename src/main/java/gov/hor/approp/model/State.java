package gov.hor.approp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "state", schema = "tiger")
public class State {

    @Id
    private String name;

    @Column(name = "stusps")
    private String abbrev;

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

    /**
     * @return the abbrev
     */
    public String getAbbrev() {
        return abbrev;
    }

    /**
     * @param abbrev the abbrev to set
     */
    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

}

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
@Table(name = "member", schema = "members")
public class Member {
    @Id
    private int id;
    private String lastname;
    private String firstname;
    private String middlename;
    @Column(name = "the_geom", columnDefinition = "Geometry")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry the_geom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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

package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import static gov.hor.approp.model.Program.CFDA_SCHEMA;
import static gov.hor.approp.model.Program.CFDA_TABLE;

@Entity
@Table(name = CFDA_TABLE, schema = CFDA_SCHEMA)
public class Agency {

    @Id
    private String agency_name;

    @JsonProperty("agencyName")
    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }
}

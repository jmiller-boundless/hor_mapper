package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import static gov.hor.approp.model.Program.CFDA_SCHEMA;
import static gov.hor.approp.model.Program.CFDA_TABLE;

@Entity
@Table(name = CFDA_TABLE, schema = CFDA_SCHEMA)
public class Bureau {

    @Id
    private String bureau_name;

    @JsonProperty("bureauName")
    public String getBureau_name() {
        return bureau_name;
    }

    public void setBureau_name(String bureau_name) {
        this.bureau_name = bureau_name;
    }
}

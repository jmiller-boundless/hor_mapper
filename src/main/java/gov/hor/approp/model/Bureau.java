package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cfda_account", schema = "programs")
public class Bureau {

    @Id
    private String bureau_code;
    private String bureau_name;

    @JsonProperty("bureauCode")
    public String getBureau_code() {
        return bureau_code;
    }

    public void setBureau_code(String bureau_code) {
        this.bureau_code = bureau_code;
    }

    @JsonProperty("bureauName")
    public String getBureau_name() {
        return bureau_name;
    }

    public void setBureau_name(String bureau_name) {
        this.bureau_name = bureau_name;
    }
}

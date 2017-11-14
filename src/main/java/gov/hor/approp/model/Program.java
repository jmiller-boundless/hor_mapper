package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cfda_account", schema = "programs")
public class Program {

    @Id
    private String cfda;
    private String program_title;

    public String getCfda() {
        return cfda;
    }

    public void setCfda(String cfda) {
        this.cfda = cfda;
    }

    @JsonProperty("title")
    public String getProgram_title() {
        return program_title;
    }

    public void setProgram_title(String program_title) {
        this.program_title = program_title;
    }

}

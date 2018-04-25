package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import static gov.hor.approp.model.Program.CFDA_SCHEMA;
import static gov.hor.approp.model.Program.CFDA_TABLE;

@Entity
@Table(name = CFDA_TABLE, schema = CFDA_SCHEMA)
public class Program {

    public static final String CFDA_SCHEMA = "programs";
    public static final String CFDA_TABLE = "cfda_account";
    public static final String CFDA_NAME = CFDA_SCHEMA + "." + CFDA_TABLE;

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

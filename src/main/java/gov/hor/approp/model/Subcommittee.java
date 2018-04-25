package gov.hor.approp.model;

import static gov.hor.approp.model.Program.CFDA_SCHEMA;
import static gov.hor.approp.model.Program.CFDA_TABLE;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CFDA_TABLE, schema = CFDA_SCHEMA)
public class Subcommittee {

    @Id
    private String subcommittee;

    public String getSubcommittee() {
        return subcommittee;
    }

    public void setSubcommittee(String subcommittee) {
        this.subcommittee = subcommittee;
    }
}

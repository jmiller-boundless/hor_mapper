package gov.hor.approp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "state", schema = "tiger")
public class State {

    @Id
    private String name;

    @Column(name = "stusps")
    private String abbrev;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

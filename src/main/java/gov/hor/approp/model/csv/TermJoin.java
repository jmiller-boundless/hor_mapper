package gov.hor.approp.model.csv;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "term", schema = "members")
public class TermJoin {

    @Id
    private Integer id;
    private String party;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * @param party the party to set
     */
    public void setParty(String party) {
        this.party = party;
    }
}

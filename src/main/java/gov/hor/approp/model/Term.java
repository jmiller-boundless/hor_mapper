package gov.hor.approp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "term", schema = "members")
public class Term {

    @Id
    private Integer id;
    private String party;
    private Integer congress;
    private String state;

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

    /**
     * @return the congress
     */
    public Integer getCongress() {
        return congress;
    }

    /**
     * @param congress the congress to set
     */
    public void setCongress(Integer congress) {
        this.congress = congress;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
}

package gov.hor.approp.model.csv;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member", schema = "members")
public class MemberJoin {

    @Id
    private Integer id;
    private String firstname;
    private String middlename;
    private String lastname;

    public String toString() {
        return firstname + " " + ((middlename != null && middlename.length() > 0) ? middlename + " " : "") + lastname;
    }

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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

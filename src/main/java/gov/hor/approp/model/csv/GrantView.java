package gov.hor.approp.model.csv;

import static gov.hor.approp.model.csv.GrantView.GRANTVIEW_SCHEMA;
import static gov.hor.approp.model.csv.GrantView.GRANTVIEW_TABLE;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = GRANTVIEW_TABLE, schema = GRANTVIEW_SCHEMA)
public class GrantView {

    public static final String GRANTVIEW_SCHEMA = "spending";
    public static final String GRANTVIEW_TABLE = "grant_geocoded_usaspending8";
    public static final String GRANTVIEW_NAME = GRANTVIEW_SCHEMA + "." + GRANTVIEW_TABLE;

    @Id
    private Integer gid;
    private String unique_transaction_id;
    private Date award_date;
    private String award_amount;
    private String award_type;
    private String fiscal_year;
    private String cfda;
    private String subcommittee;
    private String program_title;
    private String agency_name;
    private String bureau_name;
    private String recipient_name;
    private String address;
    private String city;
    private String state;
    private String statefp;
    private String zip;
    private String zip4;
    private String congress;
    @Column(name = "geocode_cascade")
    private String geocodeCascade;

    @Column(name = "cdfp")
    private String cd_at_award;

    @Column(name = "cd_currentcdfp")
    private String cd_current;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberid")
    private MemberJoin awardMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "termid")
    private TermJoin awardTerm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_currentid")
    private MemberJoin currentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_currentid")
    private TermJoin currentTerm;

    private String lat;
    private String lon;
    @Column(name = "project_description")
    private String projectDescription;

    /**
     * @return the gid
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * @return the unique_transaction_id
     */
    public String getUnique_transaction_id() {
        return unique_transaction_id;
    }

    /**
     * @param unique_transaction_id the unique_transaction_id to set
     */
    public void setUnique_transaction_id(String unique_transaction_id) {
        this.unique_transaction_id = unique_transaction_id;
    }

    /**
     * @return the award_date
     */
    public Date getAward_date() {
        return award_date;
    }

    /**
     * @param award_date the award_date to set
     */
    public void setAward_date(Date award_date) {
        this.award_date = award_date;
    }

    /**
     * @return the award_amount
     */
    public String getAward_amount() {
        return award_amount;
    }

    /**
     * @param award_amount the award_amount to set
     */
    public void setAward_amount(String award_amount) {
        this.award_amount = award_amount;
    }

    /**
     * @return the award_type
     */
    public String getAward_type() {
        return award_type;
    }

    /**
     * @param award_type the award_type to set
     */
    public void setAward_type(String award_type) {
        this.award_type = award_type;
    }

    /**
     * @return the fiscal_year
     */
    public String getFiscal_year() {
        return fiscal_year;
    }

    /**
     * @param fiscal_year the fiscal_year to set
     */
    public void setFiscal_year(String fiscal_year) {
        this.fiscal_year = fiscal_year;
    }

    /**
     * @return the cfda
     */
    public String getCfda() {
        return cfda;
    }

    /**
     * @param cfda the cfda to set
     */
    public void setCfda(String cfda) {
        this.cfda = cfda;
    }

    /**
     * @return the subcommittee
     */
    public String getSubcommittee() {
        return subcommittee;
    }

    /**
     * @param subcommittee the subcommittee to set
     */
    public void setSubcommittee(String subcommittee) {
        this.subcommittee = subcommittee;
    }

    /**
     * @return the program_title
     */
    public String getProgram_title() {
        return program_title;
    }

    /**
     * @param program_title the program_title to set
     */
    public void setProgram_title(String program_title) {
        this.program_title = program_title;
    }

    /**
     * @return the agency_name
     */
    public String getAgency_name() {
        return agency_name;
    }

    /**
     * @param agency_name the agency_name to set
     */
    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    /**
     * @return the bureau_name
     */
    public String getBureau_name() {
        return bureau_name;
    }

    /**
     * @param bureau_name the bureau_name to set
     */
    public void setBureau_name(String bureau_name) {
        this.bureau_name = bureau_name;
    }

    /**
     * @return the recipient_name
     */
    public String getRecipient_name() {
        return recipient_name;
    }

    /**
     * @param recipient_name the recipient_name to set
     */
    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
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

    /**
     * @return the statefp
     */
    public String getStatefp() {
        return statefp;
    }

    /**
     * @param statefp the statefp to set
     */
    public void setStatefp(String statefp) {
        this.statefp = statefp;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the zip4
     */
    public String getZip4() {
        return zip4;
    }

    /**
     * @param zip4 the zip4 to set
     */
    public void setZip4(String zip4) {
        this.zip4 = zip4;
    }

    /**
     * @return the congress
     */
    public String getCongress() {
        return congress;
    }

    /**
     * @param congress the congress to set
     */
    public void setCongress(String congress) {
        this.congress = congress;
    }

    /**
     * @return the geocodeCascade
     */
    public String getGeocodeCascade() {
        return geocodeCascade;
    }

    /**
     * @param geocodeCascade the geocodeCascade to set
     */
    public void setGeocodeCascade(String geocodeCascade) {
        this.geocodeCascade = geocodeCascade;
    }

    /**
     * @return the cd_at_award
     */
    public String getCd_at_award() {
        return cd_at_award;
    }

    /**
     * @param cd_at_award the cd_at_award to set
     */
    public void setCd_at_award(String cd_at_award) {
        this.cd_at_award = cd_at_award;
    }

    /**
     * @return the member_at_award
     */
    public String getMember_at_award() {
        if (awardMember != null) {
            return awardMember.toString();
        } else {
            return null;
        }
    }

    /**
     * @return the party_at_award
     */
    public String getParty_at_award() {
        if (awardTerm != null) {
            return awardTerm.getParty();
        } else {
            return null;
        }
    }

    /**
     * @return the cd_current
     */
    public String getCd_current() {
        return cd_current;
    }

    /**
     * @param cd_current the cd_current to set
     */
    public void setCd_current(String cd_current) {
        this.cd_current = cd_current;
    }

    /**
     * @return the party_current
     */
    public String getParty_current() {
        if (currentTerm != null) {
            return currentTerm.getParty();
        } else {
            return null;
        }
    }

    /**
     * @return the member_at_award
     */
    public String getMember_current() {
        if (currentMember != null) {
            return currentMember.toString();
        } else {
            return null;
        }
    }

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * @return the projectDescription
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param projectDescription the projectDescription to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

}

package gov.hor.approp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "award2", schema = "spending")
public class Award {
	@Id
	private String gid;
	private Date award_date;
	private String award_amount;
	private Integer fiscal_year;
	private String grant_or_subgrant;
	private String assistance_type;
	private String cfda;
	private String subcommittee;
	private String program_title;
	private String agency_name;
	private String bureau_name;
	private String recipient_name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String zip4;
	private String cd_at_award;
	private String member_at_award;
	private String party_at_award;
	private String cd_current;
	private String member_currentid;
	private String member_current;
	private String party_current;
	private String usa_spending_cd;
	private Double x;
	private Double y;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public Date getAward_date() {
		return award_date;
	}
	public void setAward_date(Date award_date) {
		this.award_date = award_date;
	}
	public String getAward_amount() {
		return award_amount;
	}
	public void setAward_amount(String award_amount) {
		this.award_amount = award_amount;
	}
	public Integer getFiscal_year() {
		return fiscal_year;
	}
	public void setFiscal_year(Integer fiscal_year) {
		this.fiscal_year = fiscal_year;
	}
	public String getGrant_or_subgrant() {
		return grant_or_subgrant;
	}
	public void setGrant_or_subgrant(String grant_or_subgrant) {
		this.grant_or_subgrant = grant_or_subgrant;
	}
	public String getAssistance_type() {
		return assistance_type;
	}
	public void setAssistance_type(String assistance_type) {
		this.assistance_type = assistance_type;
	}
	public String getCfda() {
		return cfda;
	}
	public void setCfda(String cfda) {
		this.cfda = cfda;
	}
	public String getSubcommittee() {
		return subcommittee;
	}
	public void setSubcommittee(String subcommittee) {
		this.subcommittee = subcommittee;
	}
	public String getProgram_title() {
		return program_title;
	}
	public void setProgram_title(String program_title) {
		this.program_title = program_title;
	}
	public String getAgency_name() {
		return agency_name;
	}
	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}
	public String getBureau_name() {
		return bureau_name;
	}
	public void setBureau_name(String bureau_name) {
		this.bureau_name = bureau_name;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZip4() {
		return zip4;
	}
	public void setZip4(String zip4) {
		this.zip4 = zip4;
	}
	public String getCd_at_award() {
		return cd_at_award;
	}
	public void setCd_at_award(String cd_at_award) {
		this.cd_at_award = cd_at_award;
	}
	public String getMember_at_award() {
		return member_at_award;
	}
	public void setMember_at_award(String member_at_award) {
		this.member_at_award = member_at_award;
	}
	public String getParty_at_award() {
		return party_at_award;
	}
	public void setParty_at_award(String party_at_award) {
		this.party_at_award = party_at_award;
	}
	public String getCd_current() {
		return cd_current;
	}
	public void setCd_current(String cd_current) {
		this.cd_current = cd_current;
	}
	public String getMember_currentid() {
		return member_currentid;
	}
	public void setMember_currentid(String member_currentid) {
		this.member_currentid = member_currentid;
	}
	public String getMember_current() {
		return member_current;
	}
	public void setMember_current(String member_current) {
		this.member_current = member_current;
	}
	public String getParty_current() {
		return party_current;
	}
	public void setParty_current(String party_current) {
		this.party_current = party_current;
	}
	public String getUsa_spending_cd() {
		return usa_spending_cd;
	}
	public void setUsa_spending_cd(String usa_spending_cd) {
		this.usa_spending_cd = usa_spending_cd;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	
	

}

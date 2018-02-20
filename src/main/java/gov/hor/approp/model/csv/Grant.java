package gov.hor.approp.model.csv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grant2", schema = "spending")
public class Grant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "unique_transaction_id")
    private String uniqueTransactionId;
    @Size(max = 255)
    @Column(name = "transaction_status")
    private String transactionStatus;
    @Size(max = 255)
    @Column(name = "fyq")
    private String fyq;
    @Size(max = 255)
    @Column(name = "cfda_program_num")
    private String cfdaProgramNum;
    @Size(max = 255)
    @Column(name = "sai_number")
    private String saiNumber;
    @Size(max = 255)
    @Column(name = "account_title")
    private String accountTitle;
    @Size(max = 255)
    @Column(name = "recipient_name")
    private String recipientName;
    @Size(max = 255)
    @Column(name = "recipient_city_code")
    private String recipientCityCode;
    @Size(max = 255)
    @Column(name = "recipient_city_name")
    private String recipientCityName;
    @Size(max = 255)
    @Column(name = "recipient_county_code")
    private String recipientCountyCode;
    @Size(max = 255)
    @Column(name = "recipient_county_name")
    private String recipientCountyName;
    @Size(max = 255)
    @Column(name = "recipient_zip")
    private String recipientZip;
    @Size(max = 255)
    @Column(name = "recipient_type")
    private String recipientType;
    @Size(max = 255)
    @Column(name = "action_type")
    private String actionType;
    @Size(max = 255)
    @Column(name = "agency_code")
    private String agencyCode;
    @Size(max = 255)
    @Column(name = "federal_award_id")
    private String federalAwardId;
    @Size(max = 255)
    @Column(name = "federal_award_mod")
    private String federalAwardMod;
    @Column(name = "fed_funding_amount")
    private BigDecimal fedFundingAmount;
    // The postgres money datatype cannot be converted (easily) to a Java type.
//    @Column(name = "non_fed_funding_amount")
//    private Double nonFedFundingAmount;
//    @Column(name = "total_funding_amount")
//    private Double totalFundingAmount;
    @Column(name = "obligation_action_date")
    @Temporal(TemporalType.DATE)
    private Date obligationActionDate;
    @Column(name = "starting_date")
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Column(name = "ending_date")
    @Temporal(TemporalType.DATE)
    private Date endingDate;
    @Size(max = 255)
    @Column(name = "assistance_type")
    private String assistanceType;
    @Size(max = 255)
    @Column(name = "record_type")
    private String recordType;
    @Size(max = 2147483647)
    @Column(name = "correction_late_ind")
    private String correctionLateInd;
    @Column(name = "fyq_correction")
    private Short fyqCorrection;
    @Size(max = 255)
    @Column(name = "principal_place_code")
    private String principalPlaceCode;
    @Size(max = 255)
    @Column(name = "principal_place_state")
    private String principalPlaceState;
    @Size(max = 255)
    @Column(name = "principal_place_cc")
    private String principalPlaceCc;
    @Size(max = 255)
    @Column(name = "principal_place_country_code")
    private String principalPlaceCountryCode;
    @Size(max = 255)
    @Column(name = "principal_place_zip")
    private String principalPlaceZip;
    @Size(max = 255)
    @Column(name = "principal_place_cd")
    private String principalPlaceCd;
    @Size(max = 2147483647)
    @Column(name = "cfda_program_title")
    private String cfdaProgramTitle;
    @Size(max = 255)
    @Column(name = "agency_name")
    private String agencyName;
    @Size(max = 2147483647)
    @Column(name = "project_description")
    private String projectDescription;
    @Size(max = 255)
    @Column(name = "duns_no")
    private String dunsNo;
    @Size(max = 255)
    @Column(name = "duns_conf_code")
    private String dunsConfCode;
    @Size(max = 255)
    @Column(name = "progsrc_agen_code")
    private String progsrcAgenCode;
    @Size(max = 255)
    @Column(name = "progsrc_acnt_code")
    private String progsrcAcntCode;
    @Size(max = 255)
    @Column(name = "progsrc_subacnt_code")
    private String progsrcSubacntCode;
    @Size(max = 255)
    @Column(name = "receip_addr1")
    private String receipAddr1;
    @Size(max = 255)
    @Column(name = "receip_addr2")
    private String receipAddr2;
    @Size(max = 255)
    @Column(name = "receip_addr3")
    private String receipAddr3;
    // The postgres money datatype cannot be converted (easily) to a Java type.
//    @Column(name = "face_loan_guran")
//    private Double faceLoanGuran;
//    @Column(name = "orig_sub_guran")
//    private Double origSubGuran;
    @Column(name = "fiscal_year")
    private Short fiscalYear;
    @Size(max = 255)
    @Column(name = "principal_place_state_code")
    private String principalPlaceStateCode;
    @Size(max = 255)
    @Column(name = "recip_cat_type")
    private String recipCatType;
    @Size(max = 255)
    @Column(name = "asst_cat_type")
    private String asstCatType;
    @Size(max = 255)
    @Column(name = "recipient_cd")
    private String recipientCd;
    @Size(max = 255)
    @Column(name = "maj_agency_cat")
    private String majAgencyCat;
    @Size(max = 255)
    @Column(name = "rec_flag")
    private String recFlag;
    @Size(max = 255)
    @Column(name = "recipient_country_code")
    private String recipientCountryCode;
    @Size(max = 255)
    @Column(name = "uri")
    private String uri;
    @Size(max = 255)
    @Column(name = "recipient_state_code")
    private String recipientStateCode;
    @Size(max = 255)
    @Column(name = "exec1_fullname")
    private String exec1Fullname;
    @Column(name = "exec1_amount")
    private Integer exec1Amount;
    @Size(max = 255)
    @Column(name = "exec2_fullname")
    private String exec2Fullname;
    @Column(name = "exec2_amount")
    private Integer exec2Amount;
    @Size(max = 255)
    @Column(name = "exec3_fullname")
    private String exec3Fullname;
    @Column(name = "exec3_amount")
    private Integer exec3Amount;
    @Size(max = 255)
    @Column(name = "exec4_fullname")
    private String exec4Fullname;
    @Column(name = "exec4_amount")
    private Integer exec4Amount;
    @Size(max = 255)
    @Column(name = "exec5_fullname")
    private String exec5Fullname;
    @Column(name = "exec5_amount")
    private Integer exec5Amount;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @Column(name = "geocode_cascade")
    private String geocodeCascade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "gid")
    private GrantView grantView;

    public Grant() {
    }

    public Grant(Integer id) {
        this.id = id;
    }

    public Grant(Integer id, String uniqueTransactionId) {
        this.id = id;
        this.uniqueTransactionId = uniqueTransactionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueTransactionId() {
        return uniqueTransactionId;
    }

    public void setUniqueTransactionId(String uniqueTransactionId) {
        this.uniqueTransactionId = uniqueTransactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getFyq() {
        return fyq;
    }

    public void setFyq(String fyq) {
        this.fyq = fyq;
    }

    public String getCfdaProgramNum() {
        return cfdaProgramNum;
    }

    public void setCfdaProgramNum(String cfdaProgramNum) {
        this.cfdaProgramNum = cfdaProgramNum;
    }

    public String getSaiNumber() {
        return saiNumber;
    }

    public void setSaiNumber(String saiNumber) {
        this.saiNumber = saiNumber;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientCityCode() {
        return recipientCityCode;
    }

    public void setRecipientCityCode(String recipientCityCode) {
        this.recipientCityCode = recipientCityCode;
    }

    public String getRecipientCityName() {
        return recipientCityName;
    }

    public void setRecipientCityName(String recipientCityName) {
        this.recipientCityName = recipientCityName;
    }

    public String getRecipientCountyCode() {
        return recipientCountyCode;
    }

    public void setRecipientCountyCode(String recipientCountyCode) {
        this.recipientCountyCode = recipientCountyCode;
    }

    public String getRecipientCountyName() {
        return recipientCountyName;
    }

    public void setRecipientCountyName(String recipientCountyName) {
        this.recipientCountyName = recipientCountyName;
    }

    public String getRecipientZip() {
        return recipientZip;
    }

    public void setRecipientZip(String recipientZip) {
        this.recipientZip = recipientZip;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getFederalAwardId() {
        return federalAwardId;
    }

    public void setFederalAwardId(String federalAwardId) {
        this.federalAwardId = federalAwardId;
    }

    public String getFederalAwardMod() {
        return federalAwardMod;
    }

    public void setFederalAwardMod(String federalAwardMod) {
        this.federalAwardMod = federalAwardMod;
    }

    public BigDecimal getFedFundingAmount() {
        return fedFundingAmount;
    }

    public void setFedFundingAmount(BigDecimal fedFundingAmount) {
        this.fedFundingAmount = fedFundingAmount;
    }

//    public Double getNonFedFundingAmount() {
//        return nonFedFundingAmount;
//    }
//
//    public void setNonFedFundingAmount(Double nonFedFundingAmount) {
//        this.nonFedFundingAmount = nonFedFundingAmount;
//    }
//
//    public Double getTotalFundingAmount() {
//        return totalFundingAmount;
//    }
//
//    public void setTotalFundingAmount(Double totalFundingAmount) {
//        this.totalFundingAmount = totalFundingAmount;
//    }
    public Date getObligationActionDate() {
        return obligationActionDate;
    }

    public void setObligationActionDate(Date obligationActionDate) {
        this.obligationActionDate = obligationActionDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getCorrectionLateInd() {
        return correctionLateInd;
    }

    public void setCorrectionLateInd(String correctionLateInd) {
        this.correctionLateInd = correctionLateInd;
    }

    public Short getFyqCorrection() {
        return fyqCorrection;
    }

    public void setFyqCorrection(Short fyqCorrection) {
        this.fyqCorrection = fyqCorrection;
    }

    public String getPrincipalPlaceCode() {
        return principalPlaceCode;
    }

    public void setPrincipalPlaceCode(String principalPlaceCode) {
        this.principalPlaceCode = principalPlaceCode;
    }

    public String getPrincipalPlaceState() {
        return principalPlaceState;
    }

    public void setPrincipalPlaceState(String principalPlaceState) {
        this.principalPlaceState = principalPlaceState;
    }

    public String getPrincipalPlaceCc() {
        return principalPlaceCc;
    }

    public void setPrincipalPlaceCc(String principalPlaceCc) {
        this.principalPlaceCc = principalPlaceCc;
    }

    public String getPrincipalPlaceCountryCode() {
        return principalPlaceCountryCode;
    }

    public void setPrincipalPlaceCountryCode(String principalPlaceCountryCode) {
        this.principalPlaceCountryCode = principalPlaceCountryCode;
    }

    public String getPrincipalPlaceZip() {
        return principalPlaceZip;
    }

    public void setPrincipalPlaceZip(String principalPlaceZip) {
        this.principalPlaceZip = principalPlaceZip;
    }

    public String getPrincipalPlaceCd() {
        return principalPlaceCd;
    }

    public void setPrincipalPlaceCd(String principalPlaceCd) {
        this.principalPlaceCd = principalPlaceCd;
    }

    public String getCfdaProgramTitle() {
        return cfdaProgramTitle;
    }

    public void setCfdaProgramTitle(String cfdaProgramTitle) {
        this.cfdaProgramTitle = cfdaProgramTitle;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDunsNo() {
        return dunsNo;
    }

    public void setDunsNo(String dunsNo) {
        this.dunsNo = dunsNo;
    }

    public String getDunsConfCode() {
        return dunsConfCode;
    }

    public void setDunsConfCode(String dunsConfCode) {
        this.dunsConfCode = dunsConfCode;
    }

    public String getProgsrcAgenCode() {
        return progsrcAgenCode;
    }

    public void setProgsrcAgenCode(String progsrcAgenCode) {
        this.progsrcAgenCode = progsrcAgenCode;
    }

    public String getProgsrcAcntCode() {
        return progsrcAcntCode;
    }

    public void setProgsrcAcntCode(String progsrcAcntCode) {
        this.progsrcAcntCode = progsrcAcntCode;
    }

    public String getProgsrcSubacntCode() {
        return progsrcSubacntCode;
    }

    public void setProgsrcSubacntCode(String progsrcSubacntCode) {
        this.progsrcSubacntCode = progsrcSubacntCode;
    }

    public String getReceipAddr1() {
        return receipAddr1;
    }

    public void setReceipAddr1(String receipAddr1) {
        this.receipAddr1 = receipAddr1;
    }

    public String getReceipAddr2() {
        return receipAddr2;
    }

    public void setReceipAddr2(String receipAddr2) {
        this.receipAddr2 = receipAddr2;
    }

    public String getReceipAddr3() {
        return receipAddr3;
    }

    public void setReceipAddr3(String receipAddr3) {
        this.receipAddr3 = receipAddr3;
    }

//    public Double getFaceLoanGuran() {
//        return faceLoanGuran;
//    }
//
//    public void setFaceLoanGuran(Double faceLoanGuran) {
//        this.faceLoanGuran = faceLoanGuran;
//    }
//
//    public Double getOrigSubGuran() {
//        return origSubGuran;
//    }
//
//    public void setOrigSubGuran(Double origSubGuran) {
//        this.origSubGuran = origSubGuran;
//    }
    public Short getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Short fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getPrincipalPlaceStateCode() {
        return principalPlaceStateCode;
    }

    public void setPrincipalPlaceStateCode(String principalPlaceStateCode) {
        this.principalPlaceStateCode = principalPlaceStateCode;
    }

    public String getRecipCatType() {
        return recipCatType;
    }

    public void setRecipCatType(String recipCatType) {
        this.recipCatType = recipCatType;
    }

    public String getAsstCatType() {
        return asstCatType;
    }

    public void setAsstCatType(String asstCatType) {
        this.asstCatType = asstCatType;
    }

    public String getRecipientCd() {
        return recipientCd;
    }

    public void setRecipientCd(String recipientCd) {
        this.recipientCd = recipientCd;
    }

    public String getMajAgencyCat() {
        return majAgencyCat;
    }

    public void setMajAgencyCat(String majAgencyCat) {
        this.majAgencyCat = majAgencyCat;
    }

    public String getRecFlag() {
        return recFlag;
    }

    public void setRecFlag(String recFlag) {
        this.recFlag = recFlag;
    }

    public String getRecipientCountryCode() {
        return recipientCountryCode;
    }

    public void setRecipientCountryCode(String recipientCountryCode) {
        this.recipientCountryCode = recipientCountryCode;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRecipientStateCode() {
        return recipientStateCode;
    }

    public void setRecipientStateCode(String recipientStateCode) {
        this.recipientStateCode = recipientStateCode;
    }

    public String getExec1Fullname() {
        return exec1Fullname;
    }

    public void setExec1Fullname(String exec1Fullname) {
        this.exec1Fullname = exec1Fullname;
    }

    public Integer getExec1Amount() {
        return exec1Amount;
    }

    public void setExec1Amount(Integer exec1Amount) {
        this.exec1Amount = exec1Amount;
    }

    public String getExec2Fullname() {
        return exec2Fullname;
    }

    public void setExec2Fullname(String exec2Fullname) {
        this.exec2Fullname = exec2Fullname;
    }

    public Integer getExec2Amount() {
        return exec2Amount;
    }

    public void setExec2Amount(Integer exec2Amount) {
        this.exec2Amount = exec2Amount;
    }

    public String getExec3Fullname() {
        return exec3Fullname;
    }

    public void setExec3Fullname(String exec3Fullname) {
        this.exec3Fullname = exec3Fullname;
    }

    public Integer getExec3Amount() {
        return exec3Amount;
    }

    public void setExec3Amount(Integer exec3Amount) {
        this.exec3Amount = exec3Amount;
    }

    public String getExec4Fullname() {
        return exec4Fullname;
    }

    public void setExec4Fullname(String exec4Fullname) {
        this.exec4Fullname = exec4Fullname;
    }

    public Integer getExec4Amount() {
        return exec4Amount;
    }

    public void setExec4Amount(Integer exec4Amount) {
        this.exec4Amount = exec4Amount;
    }

    public String getExec5Fullname() {
        return exec5Fullname;
    }

    public void setExec5Fullname(String exec5Fullname) {
        this.exec5Fullname = exec5Fullname;
    }

    public Integer getExec5Amount() {
        return exec5Amount;
    }

    public void setExec5Amount(Integer exec5Amount) {
        this.exec5Amount = exec5Amount;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getGeocodeCascade() {
        return geocodeCascade;
    }

    public void setGeocodeCascade(String geocodeCascade) {
        this.geocodeCascade = geocodeCascade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grant)) {
            return false;
        }
        Grant other = (Grant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.hor.approp.model.csv.Grant[ id=" + id + " ]";
    }

}

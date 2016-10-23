/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "country", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryCode", query = "SELECT c FROM Country c WHERE c.countryCode = :countryCode"),
    @NamedQuery(name = "Country.findByCountryName", query = "SELECT c FROM Country c WHERE c.countryName = :countryName"),
    @NamedQuery(name = "Country.findByNoBusInd", query = "SELECT c FROM Country c WHERE c.noBusInd = :noBusInd"),
    @NamedQuery(name = "Country.findByCountryNum", query = "SELECT c FROM Country c WHERE c.countryNum = :countryNum"),
    @NamedQuery(name = "Country.findByCountryStatus", query = "SELECT c FROM Country c WHERE c.countryStatus = :countryStatus"),
    @NamedQuery(name = "Country.findByIntCurrCode", query = "SELECT c FROM Country c WHERE c.intCurrCode = :intCurrCode"),
    @NamedQuery(name = "Country.findByExtCurrCode", query = "SELECT c FROM Country c WHERE c.extCurrCode = :extCurrCode"),
    @NamedQuery(name = "Country.findByCountryLimitAmt", query = "SELECT c FROM Country c WHERE c.countryLimitAmt = :countryLimitAmt"),
    @NamedQuery(name = "Country.findByCountryLimitUtilAmt", query = "SELECT c FROM Country c WHERE c.countryLimitUtilAmt = :countryLimitUtilAmt"),
    @NamedQuery(name = "Country.findByCmntNum", query = "SELECT c FROM Country c WHERE c.cmntNum = :cmntNum"),
    @NamedQuery(name = "Country.findByExposurePriorityCode", query = "SELECT c FROM Country c WHERE c.exposurePriorityCode = :exposurePriorityCode"),
    @NamedQuery(name = "Country.findByTransId", query = "SELECT c FROM Country c WHERE c.transId = :transId"),
    @NamedQuery(name = "Country.findByIsoCountryCode", query = "SELECT c FROM Country c WHERE c.isoCountryCode = :isoCountryCode")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "country_code", columnDefinition="CHAR")
    private String countryCode;
    
    @Basic(optional = false)
    @Column(name = "country_name")
    private String countryName;
    @Basic(optional = false)
    @Column(name = "no_bus_ind")
    private Character noBusInd;
    @Basic(optional = false)
    @Column(name = "country_num")
    private short countryNum;
    @Basic(optional = false)
    @Column(name = "country_status")
    private Character countryStatus;
    
    @Basic(optional = false)
    @Column(name = "int_curr_code", columnDefinition="CHAR")
    private String intCurrCode;
    
    @Basic(optional = false)
    @Column(name = "ext_curr_code", columnDefinition="CHAR")
    private String extCurrCode;
    
    @Basic(optional = false)
    @Column(name = "country_limit_amt")
    private double countryLimitAmt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "country_limit_util_amt")
    private Double countryLimitUtilAmt;
    @Column(name = "cmnt_num")
    private Integer cmntNum;
    @Basic(optional = false)
    @Column(name = "exposure_priority_code")
    private Character exposurePriorityCode;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    
    @Column(name = "iso_country_code", columnDefinition="CHAR")
    private String isoCountryCode;
    
    /*@JoinColumn(name = "calendar_code", referencedColumnName = "calendar_code")
    @ManyToOne
    private Calendar calendarCode;*/
    
    @OneToMany(mappedBy = "countryCode")
    private Collection<Commodity> commodityCollection;
    @OneToMany(mappedBy = "countryCode")
    private Collection<CreditGroup> creditGroupCollection;

    public Country() {
    }

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public Country(String countryCode, String countryName, Character noBusInd, short countryNum, Character countryStatus, String intCurrCode, String extCurrCode, double countryLimitAmt, Character exposurePriorityCode, int transId) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.noBusInd = noBusInd;
        this.countryNum = countryNum;
        this.countryStatus = countryStatus;
        this.intCurrCode = intCurrCode;
        this.extCurrCode = extCurrCode;
        this.countryLimitAmt = countryLimitAmt;
        this.exposurePriorityCode = exposurePriorityCode;
        this.transId = transId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Character getNoBusInd() {
        return noBusInd;
    }

    public void setNoBusInd(Character noBusInd) {
        this.noBusInd = noBusInd;
    }

    public short getCountryNum() {
        return countryNum;
    }

    public void setCountryNum(short countryNum) {
        this.countryNum = countryNum;
    }

    public Character getCountryStatus() {
        return countryStatus;
    }

    public void setCountryStatus(Character countryStatus) {
        this.countryStatus = countryStatus;
    }

    public String getIntCurrCode() {
        return intCurrCode;
    }

    public void setIntCurrCode(String intCurrCode) {
        this.intCurrCode = intCurrCode;
    }

    public String getExtCurrCode() {
        return extCurrCode;
    }

    public void setExtCurrCode(String extCurrCode) {
        this.extCurrCode = extCurrCode;
    }

    public double getCountryLimitAmt() {
        return countryLimitAmt;
    }

    public void setCountryLimitAmt(double countryLimitAmt) {
        this.countryLimitAmt = countryLimitAmt;
    }

    public Double getCountryLimitUtilAmt() {
        return countryLimitUtilAmt;
    }

    public void setCountryLimitUtilAmt(Double countryLimitUtilAmt) {
        this.countryLimitUtilAmt = countryLimitUtilAmt;
    }

    public Integer getCmntNum() {
        return cmntNum;
    }

    public void setCmntNum(Integer cmntNum) {
        this.cmntNum = cmntNum;
    }

    public Character getExposurePriorityCode() {
        return exposurePriorityCode;
    }

    public void setExposurePriorityCode(Character exposurePriorityCode) {
        this.exposurePriorityCode = exposurePriorityCode;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    /*public Calendar getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(Calendar calendarCode) {
        this.calendarCode = calendarCode;
    }*/

    @XmlTransient
    public Collection<Commodity> getCommodityCollection() {
        return commodityCollection;
    }

    public void setCommodityCollection(Collection<Commodity> commodityCollection) {
        this.commodityCollection = commodityCollection;
    }

    @XmlTransient
    public Collection<CreditGroup> getCreditGroupCollection() {
        return creditGroupCollection;
    }

    public void setCreditGroupCollection(Collection<CreditGroup> creditGroupCollection) {
        this.creditGroupCollection = creditGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryCode != null ? countryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryCode == null && other.countryCode != null) || (this.countryCode != null && !this.countryCode.equals(other.countryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country[ countryCode=" + countryCode + " ]";
    }
    
}

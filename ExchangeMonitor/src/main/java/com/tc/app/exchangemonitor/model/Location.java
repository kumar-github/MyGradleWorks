/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author smurugabushanam
 */
@Entity
@Table(name = "location", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByLocCode", query = "SELECT l FROM Location l WHERE l.locCode = :locCode"),
    @NamedQuery(name = "Location.findByLocName", query = "SELECT l FROM Location l WHERE l.locName = :locName"),
    @NamedQuery(name = "Location.findByOfficeLocInd", query = "SELECT l FROM Location l WHERE l.officeLocInd = :officeLocInd"),
    @NamedQuery(name = "Location.findByDelLocInd", query = "SELECT l FROM Location l WHERE l.delLocInd = :delLocInd"),
    @NamedQuery(name = "Location.findByInvLocInd", query = "SELECT l FROM Location l WHERE l.invLocInd = :invLocInd"),
    @NamedQuery(name = "Location.findByLocNum", query = "SELECT l FROM Location l WHERE l.locNum = :locNum"),
    @NamedQuery(name = "Location.findByLocStatus", query = "SELECT l FROM Location l WHERE l.locStatus = :locStatus"),
    @NamedQuery(name = "Location.findByTransId", query = "SELECT l FROM Location l WHERE l.transId = :transId"),
    @NamedQuery(name = "Location.findByLatitude", query = "SELECT l FROM Location l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Location.findByLongitude", query = "SELECT l FROM Location l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "Location.findByWarehouseAgpNum", query = "SELECT l FROM Location l WHERE l.warehouseAgpNum = :warehouseAgpNum")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "loc_code", columnDefinition="CHAR")
    private String locCode;
    @Basic(optional = false)
    @Column(name = "loc_name")
    private String locName;
    @Basic(optional = false)
    @Column(name = "office_loc_ind")
    private Character officeLocInd;
    @Basic(optional = false)
    @Column(name = "del_loc_ind")
    private Character delLocInd;
    @Basic(optional = false)
    @Column(name = "inv_loc_ind")
    private Character invLocInd;
    @Basic(optional = false)
    @Column(name = "loc_num")
    private short locNum;
    @Basic(optional = false)
    @Column(name = "loc_status")
    private Character locStatus;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "warehouse_agp_num", columnDefinition="CHAR")
    private String warehouseAgpNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locCode")
    private Collection<IctsUser> ictsUserCollection;

    public Location() {
    }

    public Location(String locCode) {
        this.locCode = locCode;
    }

    public Location(String locCode, String locName, Character officeLocInd, Character delLocInd, Character invLocInd, short locNum, Character locStatus, int transId) {
        this.locCode = locCode;
        this.locName = locName;
        this.officeLocInd = officeLocInd;
        this.delLocInd = delLocInd;
        this.invLocInd = invLocInd;
        this.locNum = locNum;
        this.locStatus = locStatus;
        this.transId = transId;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public Character getOfficeLocInd() {
        return officeLocInd;
    }

    public void setOfficeLocInd(Character officeLocInd) {
        this.officeLocInd = officeLocInd;
    }

    public Character getDelLocInd() {
        return delLocInd;
    }

    public void setDelLocInd(Character delLocInd) {
        this.delLocInd = delLocInd;
    }

    public Character getInvLocInd() {
        return invLocInd;
    }

    public void setInvLocInd(Character invLocInd) {
        this.invLocInd = invLocInd;
    }

    public short getLocNum() {
        return locNum;
    }

    public void setLocNum(short locNum) {
        this.locNum = locNum;
    }

    public Character getLocStatus() {
        return locStatus;
    }

    public void setLocStatus(Character locStatus) {
        this.locStatus = locStatus;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getWarehouseAgpNum() {
        return warehouseAgpNum;
    }

    public void setWarehouseAgpNum(String warehouseAgpNum) {
        this.warehouseAgpNum = warehouseAgpNum;
    }

    @XmlTransient
    public Collection<IctsUser> getIctsUserCollection() {
        return ictsUserCollection;
    }

    public void setIctsUserCollection(Collection<IctsUser> ictsUserCollection) {
        this.ictsUserCollection = ictsUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locCode != null ? locCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locCode == null && other.locCode != null) || (this.locCode != null && !this.locCode.equals(other.locCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location[ locCode=" + locCode + " ]";
    }
    
}

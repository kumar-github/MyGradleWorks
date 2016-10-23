/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "uom_conversion", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UomConversion.findAll", query = "SELECT u FROM UomConversion u"),
    @NamedQuery(name = "UomConversion.findByUomConvNum", query = "SELECT u FROM UomConversion u WHERE u.uomConvNum = :uomConvNum"),
    @NamedQuery(name = "UomConversion.findByUomApiVal", query = "SELECT u FROM UomConversion u WHERE u.uomApiVal = :uomApiVal"),
    @NamedQuery(name = "UomConversion.findByUomGravityVal", query = "SELECT u FROM UomConversion u WHERE u.uomGravityVal = :uomGravityVal"),
    @NamedQuery(name = "UomConversion.findByUomConvRate", query = "SELECT u FROM UomConversion u WHERE u.uomConvRate = :uomConvRate"),
    @NamedQuery(name = "UomConversion.findByUomConvOper", query = "SELECT u FROM UomConversion u WHERE u.uomConvOper = :uomConvOper"),
    @NamedQuery(name = "UomConversion.findByTransId", query = "SELECT u FROM UomConversion u WHERE u.transId = :transId")})
public class UomConversion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uom_conv_num")
    private Integer uomConvNum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uom_api_val")
    private Double uomApiVal;
    @Column(name = "uom_gravity_val")
    private Double uomGravityVal;
    @Basic(optional = false)
    @Column(name = "uom_conv_rate")
    private double uomConvRate;
    @Basic(optional = false)
    @Column(name = "uom_conv_oper")
    private Character uomConvOper;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity cmdtyCode;
    @JoinColumn(name = "uom_code_conv_from", referencedColumnName = "uom_code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uom uomCodeConvFrom;
    @JoinColumn(name = "uom_code_conv_to", referencedColumnName = "uom_code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uom uomCodeConvTo;

    public UomConversion() {
    }

    public UomConversion(Integer uomConvNum) {
        this.uomConvNum = uomConvNum;
    }

    public UomConversion(Integer uomConvNum, double uomConvRate, Character uomConvOper, int transId) {
        this.uomConvNum = uomConvNum;
        this.uomConvRate = uomConvRate;
        this.uomConvOper = uomConvOper;
        this.transId = transId;
    }

    public Integer getUomConvNum() {
        return uomConvNum;
    }

    public void setUomConvNum(Integer uomConvNum) {
        this.uomConvNum = uomConvNum;
    }

    public Double getUomApiVal() {
        return uomApiVal;
    }

    public void setUomApiVal(Double uomApiVal) {
        this.uomApiVal = uomApiVal;
    }

    public Double getUomGravityVal() {
        return uomGravityVal;
    }

    public void setUomGravityVal(Double uomGravityVal) {
        this.uomGravityVal = uomGravityVal;
    }

    public double getUomConvRate() {
        return uomConvRate;
    }

    public void setUomConvRate(double uomConvRate) {
        this.uomConvRate = uomConvRate;
    }

    public Character getUomConvOper() {
        return uomConvOper;
    }

    public void setUomConvOper(Character uomConvOper) {
        this.uomConvOper = uomConvOper;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Commodity getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(Commodity cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public Uom getUomCodeConvFrom() {
        return uomCodeConvFrom;
    }

    public void setUomCodeConvFrom(Uom uomCodeConvFrom) {
        this.uomCodeConvFrom = uomCodeConvFrom;
    }

    public Uom getUomCodeConvTo() {
        return uomCodeConvTo;
    }

    public void setUomCodeConvTo(Uom uomCodeConvTo) {
        this.uomCodeConvTo = uomCodeConvTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomConvNum != null ? uomConvNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UomConversion)) {
            return false;
        }
        UomConversion other = (UomConversion) object;
        if ((this.uomConvNum == null && other.uomConvNum != null) || (this.uomConvNum != null && !this.uomConvNum.equals(other.uomConvNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UomConversion[ uomConvNum=" + uomConvNum + " ]";
    }
    
}

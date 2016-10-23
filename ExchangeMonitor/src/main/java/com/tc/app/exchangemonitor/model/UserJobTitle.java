/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
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
@Table(name = "user_job_title", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserJobTitle.findAll", query = "SELECT u FROM UserJobTitle u"),
    @NamedQuery(name = "UserJobTitle.findByJobTitle", query = "SELECT u FROM UserJobTitle u WHERE u.jobTitle = :jobTitle"),
    @NamedQuery(name = "UserJobTitle.findByTransId", query = "SELECT u FROM UserJobTitle u WHERE u.transId = :transId")})
public class UserJobTitle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "job_title", columnDefinition="CHAR")
    private String jobTitle;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userJobTitle")
    private Collection<IctsUser> ictsUserCollection;

    public UserJobTitle() {
    }

    public UserJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public UserJobTitle(String jobTitle, int transId) {
        this.jobTitle = jobTitle;
        this.transId = transId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
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
        hash += (jobTitle != null ? jobTitle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserJobTitle)) {
            return false;
        }
        UserJobTitle other = (UserJobTitle) object;
        if ((this.jobTitle == null && other.jobTitle != null) || (this.jobTitle != null && !this.jobTitle.equals(other.jobTitle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserJobTitle[ jobTitle=" + jobTitle + " ]";
    }
    
}

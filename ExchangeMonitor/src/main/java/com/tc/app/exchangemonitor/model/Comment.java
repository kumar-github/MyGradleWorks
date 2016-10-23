package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "comment", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCmntNum", query = "SELECT c FROM Comment c WHERE c.cmntNum = :cmntNum"),
    @NamedQuery(name = "Comment.findByTinyCmnt", query = "SELECT c FROM Comment c WHERE c.tinyCmnt = :tinyCmnt"),
    @NamedQuery(name = "Comment.findByShortCmnt", query = "SELECT c FROM Comment c WHERE c.shortCmnt = :shortCmnt"),
    @NamedQuery(name = "Comment.findByCmntPath", query = "SELECT c FROM Comment c WHERE c.cmntPath = :cmntPath"),
    @NamedQuery(name = "Comment.findByCmntText", query = "SELECT c FROM Comment c WHERE c.cmntText = :cmntText"),
    @NamedQuery(name = "Comment.findByTransId", query = "SELECT c FROM Comment c WHERE c.transId = :transId")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmnt_num", nullable = false)
    private Integer cmntNum;
    
    @Column(name = "tiny_cmnt", length = 15, columnDefinition="NVARCHAR")
    private String tinyCmnt;
    
    @Column(name = "short_cmnt", length = 255, columnDefinition="NVARCHAR")
    private String shortCmnt;
    
    @Column(name = "cmnt_path", length = 255)
    private String cmntPath;
    
    @Column(name = "cmnt_text", length = 1073741823, columnDefinition="NVARCHAR")
    private String cmntText;
    
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(mappedBy = "contractCmntNum", fetch = FetchType.LAZY)
    private Collection<Account> accountCollection;

    public Comment() {
    }

    public Comment(Integer cmntNum) {
        this.cmntNum = cmntNum;
    }

    public Comment(Integer cmntNum, int transId) {
        this.cmntNum = cmntNum;
        this.transId = transId;
    }

    public Integer getCmntNum() {
        return cmntNum;
    }

    public void setCmntNum(Integer cmntNum) {
        this.cmntNum = cmntNum;
    }

    public String getTinyCmnt() {
        return tinyCmnt;
    }

    public void setTinyCmnt(String tinyCmnt) {
        this.tinyCmnt = tinyCmnt;
    }

    public String getShortCmnt() {
        return shortCmnt;
    }

    public void setShortCmnt(String shortCmnt) {
        this.shortCmnt = shortCmnt;
    }

    public String getCmntPath() {
        return cmntPath;
    }

    public void setCmntPath(String cmntPath) {
        this.cmntPath = cmntPath;
    }

    public String getCmntText() {
        return cmntText;
    }

    public void setCmntText(String cmntText) {
        this.cmntText = cmntText;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmntNum != null ? cmntNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.cmntNum == null && other.cmntNum != null) || (this.cmntNum != null && !this.cmntNum.equals(other.cmntNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.Comment[ cmntNum=" + cmntNum + " ]";
    }
    
}

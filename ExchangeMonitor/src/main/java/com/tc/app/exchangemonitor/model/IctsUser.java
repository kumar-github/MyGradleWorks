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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "icts_user", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "IctsUser.findAll", query = "SELECT i FROM IctsUser i"), @NamedQuery(name = "IctsUser.findAllActive", query = "SELECT i FROM IctsUser i where i.userStatus = 'A'"), @NamedQuery(name = "IctsUser.findByUserInit", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.userInit = :userInit"), @NamedQuery(name = "IctsUser.findByUserLastName", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.userLastName = :userLastName"), @NamedQuery(name = "IctsUser.findByUserFirstName", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.userFirstName = :userFirstName"), @NamedQuery(name = "IctsUser.findByUserLogonId", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.userLogonId = :userLogonId"), @NamedQuery(name = "IctsUser.findByUsCitizenInd", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.usCitizenInd = :usCitizenInd"),
//@NamedQuery(name = "IctsUser.findByUserStatus", query = "SELECT i FROM IctsUser i WHERE i.userStatus = :userStatus"),
@NamedQuery(name = "IctsUser.findByUserStatus", query = "SELECT i FROM IctsUser i WHERE i.userStatus = ?"), @NamedQuery(name = "IctsUser.findByUserEmployeeNum", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.userEmployeeNum = :userEmployeeNum"), @NamedQuery(name = "IctsUser.findByEmailAddress", query = "SELECT i FROM IctsUser i WHERE i.userStatus = 'A' and i.emailAddress = :emailAddress"), @NamedQuery(name = "IctsUser.findByTransId", query = "SELECT i FROM IctsUser i WHERE i.transId = :transId"), @NamedQuery(name = "IctsUser.findByUserJobTitle", query = "SELECT i FROM IctsUser i where i.userStatus = 'A' and i.userJobTitle = :userJobTitle")})

public class IctsUser implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "user_init", columnDefinition = "CHAR")
	private String userInit;
	@Basic(optional = false)
	@Column(name = "user_last_name")
	private String userLastName;
	@Basic(optional = false)
	@Column(name = "user_first_name")
	private String userFirstName;
	@Basic(optional = false)
	@Column(name = "user_logon_id")
	private String userLogonId;
	@Basic(optional = false)
	@Column(name = "us_citizen_ind")
	private Character usCitizenInd;
	@Basic(optional = false)
	@Column(name = "user_status")
	private Character userStatus;
	@Column(name = "user_employee_num")
	private Integer userEmployeeNum;
	@Column(name = "email_address")
	private String emailAddress;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;
	@JoinColumn(name = "desk_code", referencedColumnName = "desk_code")
	@ManyToOne(optional = false)
	private Desk deskCode;
	@JoinColumn(name = "loc_code", referencedColumnName = "loc_code")
	@ManyToOne(optional = false)
	private Location locCode;
	@JoinColumn(name = "user_job_title", referencedColumnName = "job_title", columnDefinition = "CHAR")
	@ManyToOne(optional = false)
	private UserJobTitle userJobTitle;

	public IctsUser()
	{
	}

	public IctsUser(final String userInit)
	{
		this.userInit = userInit;
	}

	public IctsUser(final String userInit, final String userLastName, final String userFirstName, final String userLogonId, final Character usCitizenInd, final Character userStatus, final int transId)
	{
		this.userInit = userInit;
		this.userLastName = userLastName;
		this.userFirstName = userFirstName;
		this.userLogonId = userLogonId;
		this.usCitizenInd = usCitizenInd;
		this.userStatus = userStatus;
		this.transId = transId;
	}

	public String getUserInit()
	{
		return this.userInit;
	}

	public void setUserInit(final String userInit)
	{
		this.userInit = userInit;
	}

	public String getUserLastName()
	{
		return this.userLastName;
	}

	public String userLastName()
	{
		return this.userLastName;
	}

	public void setUserLastName(final String userLastName)
	{
		this.userLastName = userLastName;
	}

	public String getUserFirstName()
	{
		return this.userFirstName;
	}

	public String userFirstName()
	{
		return this.userFirstName;
	}

	public void setUserFirstName(final String userFirstName)
	{
		this.userFirstName = userFirstName;
	}

	public String getUserLogonId()
	{
		return this.userLogonId;
	}

	public void setUserLogonId(final String userLogonId)
	{
		this.userLogonId = userLogonId;
	}

	public Character getUsCitizenInd()
	{
		return this.usCitizenInd;
	}

	public void setUsCitizenInd(final Character usCitizenInd)
	{
		this.usCitizenInd = usCitizenInd;
	}

	public Character getUserStatus()
	{
		return this.userStatus;
	}

	public void setUserStatus(final Character userStatus)
	{
		this.userStatus = userStatus;
	}

	public Integer getUserEmployeeNum()
	{
		return this.userEmployeeNum;
	}

	public void setUserEmployeeNum(final Integer userEmployeeNum)
	{
		this.userEmployeeNum = userEmployeeNum;
	}

	public String getEmailAddress()
	{
		return this.emailAddress;
	}

	public void setEmailAddress(final String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public int getTransId()
	{
		return this.transId;
	}

	public void setTransId(final int transId)
	{
		this.transId = transId;
	}

	public Desk getDeskCode()
	{
		return this.deskCode;
	}

	public void setDeskCode(final Desk deskCode)
	{
		this.deskCode = deskCode;
	}

	public Location getLocCode()
	{
		return this.locCode;
	}

	public void setLocCode(final Location locCode)
	{
		this.locCode = locCode;
	}

	public UserJobTitle getUserJobTitle()
	{
		return this.userJobTitle;
	}

	public void setUserJobTitle(final UserJobTitle userJobTitle)
	{
		this.userJobTitle = userJobTitle;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (this.userInit != null ? this.userInit.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(final Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if(!(object instanceof IctsUser))
			return false;
		final IctsUser other = (IctsUser) object;
		if(((this.userInit == null) && (other.userInit != null)) || ((this.userInit != null) && !this.userInit.equals(other.userInit)))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return this.userInit + " <--> " + this.userLogonId;
	}
}

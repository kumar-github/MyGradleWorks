package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalCommentBusiness;

@Entity
@Table(name = "external_comment", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalComment extends ExternalCommentBusiness
{
}
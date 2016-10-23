package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeTypeBusiness;

/**
 *
 * @author Saravana Kumar M
 */

@Entity
@Table(name = "external_trade_type", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalTradeType extends ExternalTradeTypeBusiness
{
}
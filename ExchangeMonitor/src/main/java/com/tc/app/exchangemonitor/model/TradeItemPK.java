/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class TradeItemPK implements Serializable {

	@Basic(optional = false)
	@Column(name = "trade_num")
	private int tradeNum;
	@Basic(optional = false)
	@Column(name = "order_num")
	private short orderNum;
	@Basic(optional = false)
	@Column(name = "item_num")
	private short itemNum;

	public TradeItemPK() {
	}

	public TradeItemPK(int tradeNum, short orderNum, short itemNum) {
		this.tradeNum = tradeNum;
		this.orderNum = orderNum;
		this.itemNum = itemNum;
	}

	public int getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}

	public short getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(short orderNum) {
		this.orderNum = orderNum;
	}

	public short getItemNum() {
		return itemNum;
	}

	public void setItemNum(short itemNum) {
		this.itemNum = itemNum;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) tradeNum;
		hash += (int) orderNum;
		hash += (int) itemNum;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TradeItemPK)) {
			return false;
		}
		TradeItemPK other = (TradeItemPK) object;
		if (this.tradeNum != other.tradeNum) {
			return false;
		}
		if (this.orderNum != other.orderNum) {
			return false;
		}
		if (this.itemNum != other.itemNum) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TradeItemPK[ tradeNum=" + tradeNum + ", orderNum=" + orderNum + ", itemNum=" + itemNum + " ]";
	}

}

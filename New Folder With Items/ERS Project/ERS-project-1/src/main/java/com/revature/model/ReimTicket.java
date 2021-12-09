package com.revature.model;

import java.util.Objects;

public class ReimTicket {

	private int reimticketId;
	private String reimticketType;
	private int reimamount;
	private int resolverId;
	private String reimInfo; //reimticket description
	private int reimauthorId;
	
	public ReimTicket() {
		super();
	}

	public ReimTicket(int reimticketId, String reimticketType, int reimamount, int resolverId, String reimInfo, int reimauthorId) {
		super();
		this.reimticketId = reimticketId;
		this.reimticketType = reimticketType;
		this.reimamount = reimamount;
		this.resolverId = resolverId;
		this.reimInfo = reimInfo; //reimticket description
		this.reimauthorId = reimauthorId;
		
	}

	public int getReimTicketId() {
		return reimticketId;
	}

	public void setReimTicketId(int reimticketId) {
		this.reimticketId = reimticketId;
	}

	public String getReimTicketType() {
		return reimticketType;
	}

	public void setReimTicketType(String reimticketType) {
		this.reimticketType = reimticketType;
	}

	public int getReimAmount() {
		return reimamount;
	}

	public void setReimAmount(int reimamount) {
		this.reimamount = reimamount;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;	
	}
//------------------------------------------------
	public String getReimInfo() {
		return reimInfo;
	}

	public void setReimInfo(String reimInfo) {
		this.reimInfo = reimInfo;
	}
//------------------------------------------------
	public int getReimAuthorId() {
		return reimauthorId;
	}

	public void setReimAuthorId(int reimauthorId) {
		this.reimauthorId = reimauthorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimticketType, reimauthorId, reimamount, resolverId, reimInfo, reimticketId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimTicket other = (ReimTicket) obj;
		return Objects.equals(reimticketType, other.reimticketType) && reimauthorId == other.reimauthorId //reiminfo added line 9
				&& reimamount == other.reimamount && resolverId == other.resolverId && Objects.equals(reimInfo, other.reimInfo) && reimticketId == other.reimticketId;
	}

	@Override
	public String toString() {
		return "ReimTicket [reimticketId=" + reimticketId + ", reimticketType=" + reimticketType + ", reimamount=" + reimamount + ", resolverId="
				+ resolverId + ", reimInfo=" + reimInfo + ", reimauthorId=" + reimauthorId + "]";
	}

}
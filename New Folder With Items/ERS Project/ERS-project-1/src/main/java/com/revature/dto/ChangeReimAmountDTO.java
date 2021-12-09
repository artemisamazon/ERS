package com.revature.dto;

import java.util.Objects;

public class ChangeReimAmountDTO {

	private int reimamount;

	public ChangeReimAmountDTO() {
		super();
	}

	public ChangeReimAmountDTO(int reimamount) {
		super();
		this.reimamount = reimamount;
	}

	public int getReimAmount() {
		return reimamount;
	}

	public void setReimAmount(int reimamount) {
		this.reimamount = reimamount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimamount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangeReimAmountDTO other = (ChangeReimAmountDTO) obj;
		return reimamount == other.reimamount;
	}

	@Override
	public String toString() {
		return "ChangeReimAmountDTO [reimamount=" + reimamount + "]";
	}
	
}
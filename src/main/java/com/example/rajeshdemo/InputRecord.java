package com.example.rajeshdemo;

import com.opencsv.bean.CsvBindByName;

public class InputRecord {
	 @CsvBindByName
	private String phoneNumber;
	 @CsvBindByName
	private String dncType;
	 @CsvBindByName
	private String callType;
	
	private Long insertId;

	 public InputRecord() {
		 
	 }
	
	public InputRecord(String phoneNumber, String dncType, String callType) {
		super();
		this.phoneNumber = phoneNumber;
		this.dncType = dncType;
		this.callType = callType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDncType() {
		return dncType;
	}

	public void setDncType(String dncType) {
		this.dncType = dncType;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	@Override
	public String toString() {
		return "InputRecord [phoneNumber=" + phoneNumber + ", dncType=" + dncType + ", callType=" + callType + "]";
	}

	public Long getInsertId() {
		return insertId;
	}

	public void setInsertId(Long insertId) {
		this.insertId = insertId;
	}
	
	
	

}

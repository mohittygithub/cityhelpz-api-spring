package com.cityhelpz.cityhelpzapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	private String objectName;
	private int statusCodeValue;
	private String message;
	private boolean success;
	private List<?> data;
	private int recordsSize;
	
	public Response() {
		super();
	}
	
	

	public Response(String objectName) {
		super();
		this.objectName = objectName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getObjectName() {
		return objectName;
	}

	@JsonProperty("object_name")
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public int getStatusCodeValue() {
		return statusCodeValue;
	}
	@JsonProperty("status_code")
	public void setStatusCodeValue(int statusCodeValue) {
		this.statusCodeValue = statusCodeValue;
	}

	public int getRecordsSize() {
		return recordsSize;
	}

	@JsonProperty("records_size")
	public void setRecordsSize(int recordsSize) {
		this.recordsSize = recordsSize;
	}
	
	
}

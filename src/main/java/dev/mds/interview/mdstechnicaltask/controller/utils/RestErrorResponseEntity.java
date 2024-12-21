package dev.mds.interview.mdstechnicaltask.controller.utils;

import java.util.Date;

public class RestErrorResponseEntity {
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public RestErrorResponseEntity(int status, String error, String message, String path) {
		super();
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}

package com.pheu.common;

public class StatusResult {
	private String id;
	private int code;
	private String message;

	public StatusResult(String id, int status, String message) {
		this.id = id;
		code = status;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return "TStatus [id=" + id + ", code=" + code + ", message=" + message + "]";
	}
}

package com.pheu.filter;

public class FilterRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 980973956459661281L;
	private int code;

	public FilterRuntimeException(int code, String message) {
		super(message);
	}

	public int getCode() {
		return code;
	}
}

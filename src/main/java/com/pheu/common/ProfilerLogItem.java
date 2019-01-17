package com.pheu.common;

public class ProfilerLogItem<T> {
	private String id;
	private T data = null;
	private long took;

	public ProfilerLogItem(String id, long took) {
		this(id, took, null);
	}

	public ProfilerLogItem(String id, long took, T data) {
		this.id = id;
		this.took = took;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTook() {
		return took;
	}

	public void setTook(long took) {
		this.took = took;
	}

	public String toString() {
		return "ProfilerLogItem [data=" + data + ", took=" + took + "]";
	}
}

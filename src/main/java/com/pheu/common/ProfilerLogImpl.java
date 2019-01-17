package com.pheu.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ProfilerLogImpl<T> implements IProfilerLog<T> {
	private LinkedHashMap<String, ProfilerLogItem<T>> data;
	private boolean isLog;

	public ProfilerLogImpl() {
		this(false);
	}

	public ProfilerLogImpl(boolean isLog) {
		data = new LinkedHashMap<>();
		this.isLog = isLog;
	}

	public void startLog(String id) {
		if (!isLog) {
			return;
		}
		data.put(id, new ProfilerLogItem<T>(id, System.currentTimeMillis()));
	}

	public void endLog(String id, T data) {
		if (!isLog) {
			return;
		}
		ProfilerLogItem<T> item = (ProfilerLogItem<T>) this.data.get(id);
		if (item == null) {
			throw new RuntimeException(String.format("Profiler: id=%s not found", new Object[] { id }));
		}
		item.setTook(System.currentTimeMillis() - item.getTook());
		item.setData(data);
		this.data.put(id, item);
	}

	public void endLog(String id) {
		if (!isLog) {
			return;
		}
		endLog(id, null);
	}

	public List<ProfilerLogItem<T>> dump() {
		if (!isLog) {
			return null;
		}
		return new ArrayList<>(this.data.values());
	}
}

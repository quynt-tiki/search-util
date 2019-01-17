package com.pheu.filter;

import com.pheu.common.IProfilerLog;
import com.pheu.common.ProfilerLogImpl;

import java.util.HashMap;
import java.util.Map;

public class ContextImpl implements IContext {
	private Map<String, String> params;
	private Map<String, Object> attrs;
	private IProfilerLog<Object> profilerLog;

	public ContextImpl() {
		this(new HashMap<String, String>());
	}

	public ContextImpl(Map<String, String> params) {
		this.params = params;
		this.attrs = new HashMap<>();
	}

	public String getParam(String key) {
		return (String) params.get(key);
	}

	public Object getAttr(String key) {
		return attrs.get(key);
	}

	public void setAttr(String key, Object value) {
		attrs.put(key, value);
	}

	public IProfilerLog<Object> getProfilerLog(boolean isEnable) {
		if (profilerLog != null) {
			return profilerLog;
		}
		profilerLog = new ProfilerLogImpl<>(isEnable);
		return profilerLog;
	}

	public IProfilerLog<Object> getProfilerLog() {
		return getProfilerLog(false);
	}
}

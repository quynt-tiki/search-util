package com.pheu.common;

import java.util.List;

public interface IProfilerLog<T> {
	public void startLog(String paramString);

	public void endLog(String paramString, T paramT);

	public void endLog(String paramString);

	public List<ProfilerLogItem<T>> dump();
}

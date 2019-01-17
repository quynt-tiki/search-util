package com.pheu.monitor;

import com.pheu.common.StatusConst;
import com.pheu.common.StatusResult;

public abstract class IMonitorTask extends java.util.TimerTask {
	protected boolean isEnabled;
	protected String id;

	public IMonitorTask(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void enable() {
		isEnabled = true;
	}

	public void disable() {
		isEnabled = false;
	}

	public void run() {
		if (isEnabled) {
			doMonitor();
		}
	}

	public StatusResult status() {
		if (isEnabled) {
			return new StatusResult(id, StatusConst.RUNNING.getCode(), StatusConst.RUNNING.getMessage());
		}
		return new StatusResult(id, StatusConst.STOPPED.getCode(), StatusConst.STOPPED.getMessage());
	}

	public abstract void doMonitor();

	public abstract long getIntervalTime();
}

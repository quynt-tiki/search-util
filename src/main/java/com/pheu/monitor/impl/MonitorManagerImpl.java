package com.pheu.monitor.impl;

import com.pheu.monitor.IMonitorManager;
import com.pheu.monitor.IMonitorTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class MonitorManagerImpl implements IMonitorManager {
	private Map<String, IMonitorTask> tasks;
	private Timer timer;

	public MonitorManagerImpl() {
		tasks = new HashMap<>();
		timer = new Timer();
	}

	public void registerTask(String id, IMonitorTask task) {
		tasks.put(id, task);
		timer.scheduleAtFixedRate(task, 0L, task.getIntervalTime());
	}

	public boolean start(String id) {
		IMonitorTask task = (IMonitorTask) tasks.get(id);
		if (task == null) {
			return false;
		}
		task.enable();
		return true;
	}

	public boolean stop(String id) {
		IMonitorTask task = (IMonitorTask) tasks.get(id);
		if (task == null) {
			return false;
		}
		task.disable();
		return true;
	}

	public java.util.List<IMonitorTask> show() {
		return new ArrayList<>(tasks.values());
	}

	public IMonitorTask get(String id) {
		return (IMonitorTask) tasks.get(id);
	}

	public boolean startAll() {
		for (String taskId : tasks.keySet()) {
			if (!start(taskId)) {
				return false;
			}
		}
		return true;
	}

	public boolean stopAll() {
		for (String taskId : tasks.keySet()) {
			if (!stop(taskId)) {
				return false;
			}
		}
		return true;
	}
}

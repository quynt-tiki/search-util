package com.pheu.monitor.impl;

import com.pheu.common.StatusConst;
import com.pheu.monitor.IMonitorTask;
import com.pheu.worker.IWorker;
import com.pheu.worker.IWorkerManager;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerMonitorTask extends IMonitorTask {
	private static Logger LOG = LoggerFactory.getLogger(WorkerMonitorTask.class);

	private long timeInterval;

	private IWorkerManager manager;

	public WorkerMonitorTask(String id, IWorkerManager manager) {
		this(id, 5000L, manager);
	}

	public WorkerMonitorTask(String id, long timeInterval, IWorkerManager manager) {
		super(id);
		this.timeInterval = timeInterval;
		this.manager = manager;
	}

	public void doMonitor() {
		if (manager != null) {
			List<IWorker> workers = manager.listWorker();
			for (IWorker worker : workers) {
				if ((worker.status().getCode() != StatusConst.RUNNING.getCode())
						&& (worker.status().getCode() != StatusConst.STARTING.getCode())) {
					worker.start();
				}
			}
		} else {
			LOG.info("Worker Manager is null!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	public long getIntervalTime() {
		return timeInterval;
	}
}

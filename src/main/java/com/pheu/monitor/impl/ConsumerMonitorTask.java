package com.pheu.monitor.impl;

import com.pheu.common.StatusConst;
import com.pheu.common.StatusResult;
import com.pheu.consumer.IConsumer;
import com.pheu.consumer.IConsumerManager;
import com.pheu.monitor.IMonitorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerMonitorTask extends IMonitorTask {
	private static Logger LOG = LoggerFactory.getLogger(ConsumerMonitorTask.class);
	private IConsumerManager consumerManager;
	private long intervalTime;

	public ConsumerMonitorTask(String id, IConsumerManager consumerManager) {
		this(id, consumerManager, 5000L);
	}

	public ConsumerMonitorTask(String id, IConsumerManager consumerManager, long intervalTime) {
		super(id);
		this.consumerManager = consumerManager;
		this.intervalTime = intervalTime;
	}

	public void doMonitor() {
		if (consumerManager != null) {
			for (IConsumer consumer : consumerManager.listConsumer()) {
				StatusResult status = consumer.status();
				if ((status.getCode() != StatusConst.RUNNING.getCode())
						&& (status.getCode() != StatusConst.STARTING.getCode())) {
					LOG.info("Start " + status.getId() + "...");
					consumer.start();
				}
			}
		}
	}

	public long getIntervalTime() {
		return intervalTime;
	}
}

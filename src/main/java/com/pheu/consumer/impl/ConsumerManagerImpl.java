package com.pheu.consumer.impl;

import com.pheu.consumer.IConsumer;
import com.pheu.consumer.IConsumerManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsumerManagerImpl implements IConsumerManager {
	private Map<String, IConsumer> consumers;

	public ConsumerManagerImpl() {
		consumers = new HashMap<>();
	}

	public String registerConsumer(String consumerId, IConsumer consumer) {
		consumers.put(consumerId, consumer);
		return consumerId;
	}

	public void removeConsumer(String consumerId) {
		consumers.remove(consumerId);
	}

	public void startConsumer(String consumerId) {
		IConsumer consumer = getConsumer(consumerId);
		if (consumer != null) {
			consumer.start();
		}
	}

	public void stopConsumer(String consumerId) {
		IConsumer consumer = getConsumer(consumerId);
		if (consumer != null) {
			consumer.stop();
		}
	}

	public IConsumer getConsumer(String consumerId) {
		return (IConsumer) consumers.get(consumerId);
	}

	public java.util.List<IConsumer> listConsumer() {
		return new ArrayList<>(consumers.values());
	}

	public void startAll() {
		for (String id : consumers.keySet()) {
			IConsumer w = getConsumer(id);
			if (w != null) {
				w.start();
			}
		}
	}

	public void stopAll() {
		for (String id : consumers.keySet()) {
			IConsumer w = getConsumer(id);
			if (w != null) {
				w.stop();
			}
		}
	}
}

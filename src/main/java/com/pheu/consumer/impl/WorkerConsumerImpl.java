package com.pheu.consumer.impl;

import com.pheu.consumer.AbstractKafkaConsumer;
import com.pheu.worker.IWorkerManager;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class WorkerConsumerImpl extends AbstractKafkaConsumer {
	private IWorkerManager workerManager;
	private String id;

	public WorkerConsumerImpl(String id, String brokerHost, String consumerGroup, List<String> topics,
			IWorkerManager workerManager) {
		this(id, brokerHost, consumerGroup, topics, "earliest", 500L, workerManager);
	}

	public WorkerConsumerImpl(String id, String brokerHost, String consumerGroup, List<String> topics,
			String autoOffsetReset, long sleep, IWorkerManager workerManager) {
		super(brokerHost, consumerGroup, topics, autoOffsetReset, sleep);
		this.id = id;
		this.workerManager = workerManager;
	}

	protected boolean processRecordsBuffer(List<ConsumerRecords<String, String>> recordsBuffer) {
		for (ConsumerRecords<String, String> records : recordsBuffer) {
			for (ConsumerRecord<String, String> record : records) {
				if (!workerManager.forward(new RecordAdapterImpl<>(record))) {
					return false;
				}
			}
		}
		return true;
	}

	public String getId() {
		return id;
	}
}

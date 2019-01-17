package com.pheu.consumer;

import com.pheu.common.Helper;
import com.pheu.common.StatusConst;
import com.pheu.common.StatusResult;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public abstract class AbstractKafkaConsumer implements IConsumer {
	private Thread thread;
	private AtomicBoolean running;
	private boolean actualStarted;
	private String brokerHost;
	private String consumerGroup;
	private List<String> topics;
	private String autoOffsetReset;
	private long sleep;
	private static final int FAIL_COUNT_EXCEED = 100;

	public AbstractKafkaConsumer(String brokerHost, String consumerGroup, List<String> topics) {
		this(brokerHost, consumerGroup, topics, "earliest", 500L);
	}

	public AbstractKafkaConsumer(String brokerHost, String consumerGroup, List<String> topics, String autoOffsetReset,
			long sleep) {
		this.brokerHost = brokerHost;
		this.consumerGroup = consumerGroup;
		this.topics = topics;
		this.autoOffsetReset = autoOffsetReset;
		running = new AtomicBoolean(false);
		actualStarted = false;
		this.sleep = sleep;
	}

	public void start() {
		if (!running.get()) {
			running.set(true);
			thread = new Thread(this);
			thread.start();
		}
	}

	public StatusResult status() {
		StatusConst status;
		if (running.get()) {
			if (actualStarted) {
				status = StatusConst.RUNNING;
			} else {
				status = StatusConst.STARTING;
			}
		} else {
			status = StatusConst.STOPPED;
		}
		return new StatusResult("consumer-default", status.getCode(), status.getMessage());
	}

	public void stop() {
		running.set(false);
		actualStarted = false;
		thread = null;
	}

	public void run() {
		Properties props = new Properties();
		props.put("bootstrap.servers", brokerHost);
		props.put("group.id", consumerGroup);
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("auto.offset.reset", autoOffsetReset);

		Consumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(topics);

		doConsume(consumer);

		consumer.close();
	}

	protected void doConsume(Consumer<String, String> consumer) {
		List<ConsumerRecords<String, String>> recordsBuffer = new ArrayList<>();
		int failCount = 0;

		while (running.get()) {
			if (!actualStarted) {
				actualStarted = true;
			}
			ConsumerRecords<String, String> records = null;
			if (failCount != FAIL_COUNT_EXCEED) {
				records = consumer.poll(Duration.ofMillis(100L));
			}
			if (records == null || records.isEmpty()) {
				Helper.sleep(sleep);
			} else {
				recordsBuffer.add(records);
			}

			boolean flagSuccess = processRecordsBuffer(recordsBuffer);

			if (flagSuccess) {
				recordsBuffer.clear();
			} else {
				failCount++;
			}
		}
	}

	protected abstract boolean processRecordsBuffer(List<ConsumerRecords<String, String>> paramList);
}

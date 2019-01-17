package com.pheu.consumer.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.pheu.common.IRecordAdapter;

public class RecordAdapterImpl<T> implements IRecordAdapter {
	private String key;
	private T value;

	public RecordAdapterImpl(ConsumerRecord<String, T> record) {
		this.key = ((String) record.key());
		this.value = record.value();
	}

	public RecordAdapterImpl(String key, T value) {
		this.key = key;
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getKey() {
		return this.key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		return this.value;
	}

	
}

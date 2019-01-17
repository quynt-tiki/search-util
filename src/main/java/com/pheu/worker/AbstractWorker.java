package com.pheu.worker;

import com.pheu.common.Helper;
import com.pheu.common.IRecordAdapter;
import com.pheu.common.StatusConst;
import com.pheu.common.StatusResult;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractWorker<T> implements IWorker {
	protected String id;
	private boolean actualStarted;
	private LinkedHashMap<String, T> buffer;
	private long lastCommit;
	private int batchSize;
	private long intervalTime;
	private long sleep;
	private Queue<IRecordAdapter> queue;
	private AtomicBoolean running;
	private Thread thread;

	public AbstractWorker(String id) {
		this(id, 10, 10000L, 500L);
	}

	public AbstractWorker(String id, int batchSize, long intervalKickOff, long sleep) {
		this.id = id;
		this.batchSize = batchSize;
		intervalTime = intervalKickOff;
		this.sleep = sleep;
		actualStarted = false;
		queue = new ConcurrentLinkedQueue<>();
		running = new AtomicBoolean(false);
		buffer = new LinkedHashMap<>();
	}

	public void start() {
		running.set(true);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		running.set(false);
		actualStarted = false;
		thread = null;
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
		return new StatusResult(id, status.getCode(), status.getMessage());
	}

	public boolean offerTask(IRecordAdapter recordAdapter) {
		boolean result = queue.offer(recordAdapter);
		return result;
	}

	public void run() {
		while (running.get()) {
			if (!actualStarted) {
				actualStarted = true;
			}

			IRecordAdapter adapter = (IRecordAdapter) queue.peek();
			if (adapter == null) {
				process(adapter);
				Helper.sleep(sleep);
			} else {
				process(adapter);
				queue.remove();
			}
		}
	}

	private void process(IRecordAdapter adapter) {
		if (adapter != null) {
			Map<String, T> transformMap = map(adapter);
			if (!transformMap.isEmpty()) {
				buffer.putAll(transformMap);
			}
		}
		if ((buffer.size() >= batchSize) || (System.currentTimeMillis() - lastCommit >= intervalTime)) {
			reduce(buffer);
			flush();
		}
	}

	public void flush() {
		buffer.clear();
		lastCommit = System.currentTimeMillis();
	}

	protected abstract LinkedHashMap<String, T> map(IRecordAdapter paramIRecordAdapter);

	protected abstract void reduce(LinkedHashMap<String, T> paramLinkedHashMap);
}

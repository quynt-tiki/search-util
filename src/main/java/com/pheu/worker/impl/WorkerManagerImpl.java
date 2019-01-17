package com.pheu.worker.impl;

import com.pheu.common.IRecordAdapter;
import com.pheu.worker.IWorker;
import com.pheu.worker.IWorkerManager;
import com.pheu.worker.IWorkerRouter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerManagerImpl implements IWorkerManager {
	private IWorkerRouter workerRouter;
	private Map<String, IWorker> workerHandlers;

	public WorkerManagerImpl(IWorkerRouter workerRouter) {
		this.workerRouter = workerRouter;
		workerHandlers = new HashMap<>();
	}

	public String registerWorker(String workerId, IWorker worker) {
		workerHandlers.put(workerId, worker);
		workerRouter.addWorker(workerId, worker);
		return workerId;
	}

	public void removeWorker(String workerId) {
		workerHandlers.remove(workerId);
		workerRouter.removeWorker(workerId);
	}

	public boolean forward(IRecordAdapter adapter) {
		IWorker handler = workerRouter.chooseWorker((String) adapter.getKey());
		if (handler != null) {
			return handler.offerTask(adapter);
		}
		return false;
	}

	public IWorker getWorkerHandler(String workerId) {
		IWorker worker = (IWorker) workerHandlers.get(workerId);
		if (worker == null) {
			throw new RuntimeException(String.format("Worder #%s is null", new Object[] { workerId }));
		}
		return worker;
	}

	public List<IWorker> listWorker() {
		return new ArrayList<>(workerHandlers.values());
	}

	public void startWorker(String workerId) {
		IWorker worker = getWorkerHandler(workerId);
		if (worker != null) {
			worker.start();
		}
	}

	public void stopWorker(String workerId) {
		IWorker worker = getWorkerHandler(workerId);
		if (worker != null) {
			worker.stop();
		}
	}

	public void startAll() {
		for (String workerId : workerHandlers.keySet()) {
			IWorker w = getWorkerHandler(workerId);
			if (w != null) {
				w.start();
			}
		}
	}

	public void stopAll() {
		for (String workerId : workerHandlers.keySet()) {
			IWorker w = getWorkerHandler(workerId);
			if (w != null) {
				w.stop();
			}
		}
	}
}

package com.pheu.worker.impl;

import com.pheu.worker.IWorker;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RoundRobinWorkerRouterImpl implements com.pheu.worker.IWorkerRouter {
	public LinkedHashMap<String, IWorker> workers;
	private int currentIndex;

	public RoundRobinWorkerRouterImpl() {
		this(new ArrayList<IWorker>());
	}

	public RoundRobinWorkerRouterImpl(List<IWorker> workers) {
		for (IWorker worker : workers) {
			this.workers.put(worker.getId(), worker);
		}
		currentIndex = 0;
	}

	public IWorker chooseWorker(String key) {
		if (workers.isEmpty()) {
			return null;
		}
		int oldIndex = currentIndex;
		currentIndex += 1;
		if (currentIndex >= workers.size()) {
			currentIndex = 0;
		}
		return (IWorker) new ArrayList<>(workers.values()).get(oldIndex);
	}

	public void addWorker(String id, IWorker worker) {
		workers.put(id, worker);
		currentIndex = 0;
	}

	public void removeWorker(String id) {
		workers.remove(id);
		currentIndex = 0;
	}
}

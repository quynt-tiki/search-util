package com.pheu.worker.impl;

import com.github.jaskey.consistenthash.ConsistentHashRouter;
import com.pheu.common.IRecordAdapter;
import com.pheu.common.StatusResult;
import com.pheu.worker.IWorker;
import com.pheu.worker.IWorkerRouter;

import java.util.ArrayList;

public class ConsistentHashWorkerRouterImpl<K, V> implements IWorkerRouter {
	private ConsistentHashRouter<IWorker> consistentHashRouter;
	private int vNodeCount;

	public ConsistentHashWorkerRouterImpl(int vNodeCount) {
		this.vNodeCount = vNodeCount;
		consistentHashRouter = new ConsistentHashRouter<>(new ArrayList<IWorker>(), this.vNodeCount);
	}

	public IWorker chooseWorker(String key) {
		return (IWorker) consistentHashRouter.routeNode(key);
	}

	public void addWorker(String id, IWorker workers) {
		consistentHashRouter.addNode(workers, vNodeCount);
	}

	public void removeWorker(final String id) {
		IWorker worker = new IWorker() {
			public void run() {
			}

			public String getKey() {
				return id;
			}

			public void start() {
			}

			public void stop() {
			}

			public StatusResult status() {
				return null;
			}

			public boolean offerTask(IRecordAdapter adapter) {
				return false;
			}

			public String getId() {
				return null;
			}
		};
		consistentHashRouter.removeNode(worker);
	}
}

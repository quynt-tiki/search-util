package com.pheu.worker;

import com.github.jaskey.consistenthash.Node;
import com.pheu.common.IRecordAdapter;
import com.pheu.common.StatusResult;

public interface IWorker
  extends Runnable, Node
{
  public void start();
  
  public void stop();
  
  public StatusResult status();
  
  public <K, V> boolean offerTask(IRecordAdapter paramIRecordAdapter);
  
  public String getId();
}

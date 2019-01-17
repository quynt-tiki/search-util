package com.pheu.worker;

import com.pheu.common.IRecordAdapter;
import java.util.List;

public interface IWorkerManager
{
  public String registerWorker(String paramString, IWorker paramIWorker);
  
  public void removeWorker(String paramString);
  
  public <K, V> boolean forward(IRecordAdapter paramIRecordAdapter);
  
  public void startWorker(String paramString);
  
  public void stopWorker(String paramString);
  
  public IWorker getWorkerHandler(String paramString);
  
  public List<IWorker> listWorker();
  
  public void startAll();
  
  public void stopAll();
}

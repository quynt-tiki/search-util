package com.pheu.worker;

public interface IWorkerRouter
{
  public IWorker chooseWorker(String paramString);
  
  public void addWorker(String paramString, IWorker paramIWorker);
  
  public void removeWorker(String paramString);
}

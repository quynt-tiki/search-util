package com.pheu.monitor;

import java.util.List;

public interface IMonitorManager
{
  public void registerTask(String paramString, IMonitorTask paramIMonitorTask);
  
  public boolean start(String paramString);
  
  public boolean stop(String paramString);
  
  public boolean startAll();
  
  public boolean stopAll();
  
  public List<IMonitorTask> show();
  
  public IMonitorTask get(String paramString);
}

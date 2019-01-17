package com.pheu.consumer;

import com.pheu.common.StatusResult;

public interface IConsumer
  extends Runnable
{
  public void start();
  
  public void stop();
  
  public StatusResult status();
  
  public String getId();
}

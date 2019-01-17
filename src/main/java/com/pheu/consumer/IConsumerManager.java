package com.pheu.consumer;

import java.util.List;

public interface IConsumerManager
{
  public String registerConsumer(String paramString, IConsumer paramIConsumer);
  
  public void removeConsumer(String paramString);
  
  public void startConsumer(String paramString);
  
  public void stopConsumer(String paramString);
  
  public IConsumer getConsumer(String paramString);
  
  public List<IConsumer> listConsumer();
  
  public void startAll();
  
  public void stopAll();
}

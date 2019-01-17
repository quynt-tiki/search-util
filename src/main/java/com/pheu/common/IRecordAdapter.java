package com.pheu.common;

public interface IRecordAdapter
{
  public <K> K getKey();
  
  public <V> V getValue();
}

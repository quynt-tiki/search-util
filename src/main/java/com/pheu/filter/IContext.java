package com.pheu.filter;

import com.pheu.common.IProfilerLog;

public interface IContext
{
  public void setAttr(String paramString, Object paramObject);
  
  public Object getAttr(String paramString);
  
  public String getParam(String paramString);
  
  public IProfilerLog<Object> getProfilerLog(boolean paramBoolean);
  
  public IProfilerLog<Object> getProfilerLog();
}

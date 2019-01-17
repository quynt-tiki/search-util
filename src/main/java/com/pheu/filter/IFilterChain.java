package com.pheu.filter;

public interface IFilterChain
{
  public IResponse execute(IContext paramIContext);
  
  public void registerFilter(IFilter paramIFilter);
}

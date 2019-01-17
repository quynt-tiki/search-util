package com.pheu.filter;

public enum FilterExceptionCode {
  OK(200, "OK"), 
  FAIL(500, "FAIL"), 
  NOT_FOUND(404, "NOT FOUND"), 
  INVALID(400, "INVALID"), 
  NO_CONTENT(204, "NO CONTENT"), 
  FILTER_NULL(1001, "FILTER NULL");
  
  private int status;
  private String message;
  
  private FilterExceptionCode(int status, String message) {
    this.status = status;
    this.message = message;
  }
  
  public int getStatus() {
    return status;
  }
  
  public String getMessage() {
    return message;
  }
}

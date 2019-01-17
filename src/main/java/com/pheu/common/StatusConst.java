package com.pheu.common;

public enum StatusConst {
  STARTING(0, "STARTING"), 
  RUNNING(1, "RUNNING"), 
  STOPPED(-1, "STOPPED");
  
  private int code;
  private String message;
  
  private StatusConst(int code, String message) {
    this.code = code;
    this.message = message;
  }
  
  public int getCode() {
    return code;
  }
  
  public String getMessage() {
    return message;
  }
}

package br.com.gm.worklog.model;

import lombok.Getter;

public enum LogStatuses {
  
  OPEN(1),
  FINISHED(2),
  MODIFIED(3);

  LogStatuses(long id) {
    this.id = id;
  }

  @Getter
  public long id;
}
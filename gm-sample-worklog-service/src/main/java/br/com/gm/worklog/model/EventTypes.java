package br.com.gm.worklog.model;

import lombok.Getter;
/*
 * not entirely happy with that one, but will do the job for now
 */
public enum EventTypes {

  USER_REGISTER(1),
  USER_LOGIN(2),
  USER_UPDATE(3),
  USER_REMOVE(4),
  WORKLOG_CREATE(5),
  WORKLOG_CLOSE(6),
  WORKLOG_MODIFY(7),
  WORKLOG_REMOVE(8);

  EventTypes(long id) {
    this.id = id;
  }

  @Getter
  private long id;
  
}
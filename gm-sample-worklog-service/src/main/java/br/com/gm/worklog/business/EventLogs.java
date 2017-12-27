package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.EventLog;
import br.com.gm.worklog.model.EventType;
import br.com.gm.worklog.model.EventTypes;
import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.WorkLog;
import java.util.List;

@Repository
public class EventLogs {

  @PersistenceContext
  private EntityManager em;

  @Cacheable("eventlogs")
  public List<EventLog> listbyUser(int start, int size) {
    return em.createQuery("select e from EventLog e", EventLog.class)//
        .setFirstResult(start).setMaxResults(size).getResultList();
  }

  @Cacheable("eventlog")
  public EventLog find(Long eventLogId) {
    return em.find(EventLog.class, eventLogId);
  }

  @Transactional
  public EventLog save(EventLog ev) {
    return em.merge(ev);
  }

  @Transactional
  public int del(Long eventLogId) {
    return em.createQuery("delete from EventLog ev where ev.eventLogId = :id")//
        .setParameter("id", eventLogId).executeUpdate();
  }

  @Transactional
  public EventLog saveUserCreation(VwUser vu) {
    EventLog ev = new EventLog();
    ev.setEventLogDescription(vu.toString());
    ev.setType(new EventType(EventTypes.USER_REGISTER));
    ev.setEventLogDescription(vu.toString());
    return save(ev);
  }

  @Transactional
  public EventLog saveUserLogin(VwUser vu) {
    EventLog ev = new EventLog();
    ev.setType(new EventType(EventTypes.USER_LOGIN));
    ev.setEventLogDescription(vu.toString());
    return save(ev);
  }

  @Transactional
  public EventLog saveUserModification(VwUser vu) {
    EventLog ev = new EventLog();
    ev.setType(new EventType(EventTypes.USER_UPDATE));
    ev.setEventLogDescription(vu.toString());
    return save(ev);
  }

  @Transactional
  public EventLog seveUserDeletion(Long userId) {
    EventLog ev = new EventLog();
    ev.setType(new EventType(EventTypes.USER_REMOVE));
    ev.setEventLogDescription(userId.toString());
    return save(ev);
  }

  @Transactional
  public EventLog saveWorkLogCreation(WorkLog workLog) {
    EventLog ev = new EventLog();
    ev.setType(new EventType(EventTypes.WORKLOG_CREATE));
    ev.setEventLogDescription(workLog.toString());
    return save(ev);
  }

}
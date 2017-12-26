package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.EventLog;
import br.com.gm.worklog.model.EventType;
import br.com.gm.worklog.model.EventTypes;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.WorkLog;
import java.util.List;

@Repository
public class EventLogs {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private Users users;

  public List<EventLog> listbyUser(Long userId, int start, int size) {
    return em.createQuery("select e from EventLog e where e.user.userId = :id", EventLog.class)//
        .setFirstResult(start).setMaxResults(size).setParameter("id", userId).getResultList();
  }

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
  public EventLog saveUserCreation(User newUser, VwUser author) {
    EventLog ev = new EventLog();
    ev.setUser(author);
    ev.setType(new EventType(EventTypes.USER_REGISTER));

    VwUser vu = users.find(newUser.getUserId());
    ev.setEventLogDescription(vu.toString());

    return save(ev);
  }

  @Transactional
  public EventLog saveUserModification(User newUser, VwUser author) {
    EventLog ev = new EventLog();
    ev.setUser(author);
    ev.setType(new EventType(EventTypes.USER_UPDATE));

    VwUser vu = users.find(newUser.getUserId());
    ev.setEventLogDescription(vu.toString());

    return save(ev);
  }

  @Transactional
  public EventLog seveUserDeletion(Long userId, VwUser author) {
    EventLog ev = new EventLog();
    ev.setUser(author);
    ev.setType(new EventType(EventTypes.USER_UPDATE));

    ev.setEventLogDescription(userId.toString());
    
    return save(ev);
  }

  @Transactional
  public EventLog saveWorkLogCreation(WorkLog workLog, VwUser author) {
    EventLog ev = new EventLog();
    ev.setUser(author);
    ev.setType(new EventType(EventTypes.WORKLOG_CREATE));
    // TODO the author as soon as auth enters in this theater
    return save(ev);
  }

}
package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.EventLog;

import java.util.List;

@Repository
@Transactional
public class EventLogs {
  
  @PersistenceContext
  private EntityManager em;

  public List<EventLog> listbyUser(Long userId, int start, int size) {
    return em.createQuery("select e from EventLog e where e.user.userId = :id", EventLog.class)
    .setFirstResult(start)
    .setMaxResults(size)
    .setParameter("id",userId) 
    .getResultList();
  }
  
}
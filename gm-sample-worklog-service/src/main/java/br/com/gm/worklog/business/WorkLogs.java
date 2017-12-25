package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.WorkLog;

import java.util.List;

@Repository
@Transactional
public class WorkLogs {
  
  @PersistenceContext
  private EntityManager em;

  public List<WorkLog> listbyUser(Long userId, int start, int size){
    return em.createQuery("select w from WorkLog w where w.user.userId = :id", WorkLog.class)
    .setFirstResult(start)
    .setMaxResults(size)
    .setParameter("id",userId) 
    .getResultList();
  }

  public void save(WorkLog w) {
    em.persist(w);
  }

  public int del(Long workLogId) {
    return em.createQuery("delete from WorkLog w where w.workLogId = :id")
      .setParameter("id",workLogId).executeUpdate();
  }
  
}
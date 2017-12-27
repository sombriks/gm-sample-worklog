package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.LogStatus;
import br.com.gm.worklog.model.LogStatuses;
import br.com.gm.worklog.model.WorkLog;

import java.util.List;

import lombok.SneakyThrows;

@Repository
public class WorkLogs {

  @PersistenceContext
  private EntityManager em;

  public List<WorkLog> listbyUser(Long userId, int start, int size) {
    return em.createQuery("select w from WorkLog w where w.user.userId = :id", WorkLog.class)//
        .setFirstResult(start).setMaxResults(size).setParameter("id", userId).getResultList();
  }

  public WorkLog find(Long workLogId) {
    return em.find(WorkLog.class, workLogId);
  }

  @SneakyThrows
  @Transactional
  public WorkLog save(WorkLog w) {
    if (overlaps(w).size() == 0) {
      processStatuses(w);
      return em.merge(w);
    } else
      throw new Exception("[" + w + "] overlaps another WorkLog");
  }

  @Transactional
  public int del(Long workLogId) {
    return em.createQuery("delete from WorkLog w where w.workLogId = :id").setParameter("id", workLogId)
        .executeUpdate();
  }

  private List<WorkLog> overlaps(WorkLog w) throws Exception {
    String q = "select w from WorkLog w where " + "(w.workLogStart between :start and :end "
        + "or w.workLogFinish between :start and :end) " + "and w.user.userId = :id";
    return em.createQuery(q, WorkLog.class).setParameter("start", w.getWorkLogStart())
        .setParameter("end", w.getWorkLogFinish()).setParameter("id", w.getUser().getUserId()).getResultList();
  }

  private void processStatuses(WorkLog w) {
    if (w.getWorkLogFinish() != null) {
      if (w.getStatus().getLogStatusId() == LogStatuses.OPEN.getId()) {
        w.setStatus(new LogStatus(LogStatuses.FINISHED));
      } else if (w.getStatus().getLogStatusId() == LogStatuses.FINISHED.getId()) {
        w.setStatus(new LogStatus(LogStatuses.MODIFIED));
      }
    }
  }
}
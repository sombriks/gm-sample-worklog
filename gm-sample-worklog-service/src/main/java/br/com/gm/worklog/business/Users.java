package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.gm.worklog.model.VwUser;

import java.util.List;

@Repository
@Transactional
public class Users {
  
  @PersistenceContext
  private EntityManager em;

  public List<VwUser> list(String query, int start, int size){
    return em.createQuery("select u from VwUser u where u.userLogin like :query", VwUser.class)
    .setFirstResult(start)
    .setMaxResults(size)
    .setParameter("query",String.format("%%%s%%",query))
    .getResultList();
  }
  
}
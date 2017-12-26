package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;

import java.util.List;

@Repository
public class Users {

  @PersistenceContext
  private EntityManager em;

  public List<VwUser> listByName(String query, int start, int size) {
    return em.createQuery("select u from VwUser u where u.userLogin like :query", VwUser.class)
      .setParameter("query", String.format("%%%s%%", query))
      .setFirstResult(start)
      .setMaxResults(size)
      .getResultList();
  }

  public VwUser find(Long userId) {
    return em.find(VwUser.class, userId);
  }

  @Transactional
  public User save(User u) {
    return em.merge(u);
  }

  // remove and persist are pretty useless on a RESTFul party
  // public void del(User u) {
  //   em.remove(u);
  // }

  @Transactional
  public int del(Long userId) {
    return em.createQuery("delete from User u where u.userId = :id")
      .setParameter("id",userId).executeUpdate();
  }

}
package br.com.gm.worklog.business;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;

import java.util.List;

@Repository
public class Users {

  @PersistenceContext
  private EntityManager em;

  @Cacheable("users")
  public List<VwUser> listByName(String query, int start, int size) {
    return em.createQuery("select u from VwUser u where u.userLogin like :query", VwUser.class)
        .setParameter("query", "%" + query + "%")//
        .setFirstResult(start).setMaxResults(size).getResultList();
  }

  @Cacheable("user")
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
    return em.createQuery("delete from User u where u.userId = :id").setParameter("id", userId).executeUpdate();
  }

  public User findByLoginAndHash(String userLogin, String userHash) {
    String q = "select u from User u where u.userLogin = :login and u.userHash = :hash";
    return em.createQuery(q, User.class)//
        .setParameter("login", userLogin)//
        .setParameter("hash", userHash)//
        .getSingleResult();
  }

  public User findbyLogin(String userLogin) {
    String q = "select u from User u where u.userLogin = :login";
    return em.createQuery(q, User.class)//
        .setParameter("login", userLogin)//
        .getSingleResult();
  }

}
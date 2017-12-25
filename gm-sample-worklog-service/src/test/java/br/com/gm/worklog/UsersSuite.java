package br.com.gm.worklog;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersSuite {

  @Autowired
  private Users users;

  private User insertUser() {
    User u = new User();
    u.setUserLogin("foo" + System.currentTimeMillis());
    u.setUserName("Mr. Foo Bar");
    u.setUserHash(DigestUtils.md5Hex("e1e2e3e4"));
    users.save(u);
    return u;
  }

  @Test
  public void shouldInsertOneUser() throws Exception {
    User u = insertUser();
    assertNotNull(u.getUserId());
    users.del(u.getUserId()); // we must guarantee idempotency
  }

  @Test
  public void shouldListUsers() throws Exception {
    List<User> list = new ArrayList<>();
    list.add(insertUser());
    list.add(insertUser());
    list.add(insertUser());
    list.add(insertUser());
    list.add(insertUser());
    assertEquals(list.size(), users.listByName("foo", 0, 10).size());
    list.forEach(u -> users.del(u.getUserId()));
  }

  @Test
  public void shouldDeleteOneUser() throws Exception {
    User u = insertUser();
    users.del(u.getUserId());
    VwUser vu = users.find(u.getUserId());
    assertNull(vu);
  }
}
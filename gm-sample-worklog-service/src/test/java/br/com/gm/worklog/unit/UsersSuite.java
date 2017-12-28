package br.com.gm.worklog.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.gm.worklog.TestUtil;
import br.com.gm.worklog.business.Users;

import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersSuite {

  @Autowired
  private Users users;

  @Autowired
  private TestUtil util;

  @Test
  public void shouldInsertOneUser() throws Exception {
    User u = util.insertUser();
    assertNotNull(u.getUserId());
    users.del(u.getUserId()); // we must guarantee idempotency
  }

  @Test
  public void shouldListUsers() throws Exception {
    List<User> list = new ArrayList<>();
    int i = 10;
    while(i-->0)
      list.add(util.insertUser());
    assertEquals(list.size(), users.listByLogin("foo", 0, 10).size());
    list.forEach(u -> users.del(u.getUserId()));
  }

  @Test
  public void shouldDeleteOneUser() throws Exception {
    User u = util.insertUser();
    users.del(u.getUserId());
    VwUser vu = users.find(u.getUserId());
    assertNull(vu);
  }

  @Test(expected = Exception.class)
  public void shouldNotBeAbleToSaveTwoUsersWithSameLogin() throws Exception {
    User u1 = util.insertUser("joe");
    User u2 = util.insertUser("joe");
    // this never happens
    u1.setUserHash("userHash");
    u2.setUserHash("userHash"); 
  }

}
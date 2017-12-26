package br.com.gm.worklog.unit;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gm.worklog.TestUtil;

import br.com.gm.worklog.business.EventLogs;
import br.com.gm.worklog.business.Users;

import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.EventLog;
import br.com.gm.worklog.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventLogsSuite {

  @Autowired
  private TestUtil util;

  @Autowired
  private Users users;

  @Autowired
  private EventLogs evLogs;

  @Test
  public void shouldSaveEvent() throws Exception {
    EventLog ev = util.insertEventLog();
    assertNotNull(ev.getEventLogId());
    evLogs.del(ev.getEventLogId());
  }

  @Test
  public void shouldDeleteEvent() throws Exception {
    EventLog ev = util.insertEventLog();
    evLogs.del(ev.getEventLogId());
    assertNull(evLogs.find(ev.getEventLogId()));
  }

  @Test
  public void shouldListeventLogs() throws Exception {
    List<EventLog> list = new ArrayList<>();
    int i = 10;

    User u = util.insertUser();
    VwUser vu = users.find(u.getUserId());

    while (i-- > 0)
      list.add(util.insertEventLog(vu));

    assertEquals(list.size(), evLogs.listbyUser(0, 10).size());

    list.forEach(e -> evLogs.del(e.getEventLogId()));
  }

  @Test
  public void shouldSaveUserCreationEvent() throws Exception {
    User u = util.insertUser();
    VwUser author = users.find(u.getUserId()); // someone to blame when we audit it
    EventLog ev = evLogs.saveUserCreation(u);
    assertEquals("USER_REGISTER", ev.getType().getEventTpeDescription());
  }

}
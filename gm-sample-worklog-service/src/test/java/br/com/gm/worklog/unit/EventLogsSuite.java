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
  private EventLogs evLogs;

  @Test
  public void shouldSaveOneEvent() throws Exception {
    EventLog ev = util.insertEventLog();
    assertNotNull(ev.getEventLogId());
    evLogs.del(ev.getEventLogId());
  }



}
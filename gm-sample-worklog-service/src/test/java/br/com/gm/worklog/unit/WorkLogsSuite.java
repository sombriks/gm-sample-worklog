package br.com.gm.worklog.unit;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gm.worklog.business.WorkLogs;
import br.com.gm.worklog.TestUtil;
import br.com.gm.worklog.business.Users;

import br.com.gm.worklog.model.WorkLog;
import br.com.gm.worklog.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkLogsSuite {

  @Autowired
  private Users users;

  @Autowired
  private WorkLogs wLogs;

  @Autowired
  private TestUtil util;

  @Test
  public void shouldSaveOneWorkLog() throws Exception {

    WorkLog w = util.insertWorkLog();
    assertNotNull(w.getWorkLogId());
    wLogs.del(w.getWorkLogId());

  }

  @Test
  public void shouldListworklogs() throws Exception {
    
    int i = 10;
    User u = util.insertUser();
    List<WorkLog> list = new ArrayList<>();

    while (i-- > 0)
      list.add(util.insertWorkLog(u, i));

    assertEquals(list.size(), wLogs.listbyUser(u.getUserId(), 0, 10).size());

    list.forEach(w -> wLogs.del(w.getWorkLogId()));

    users.del(u.getUserId());
  }

  @Test
  public void shouldDeleteOneWorkLog() throws Exception {

    WorkLog w = util.insertWorkLog();

    wLogs.del(w.getWorkLogId());

    assertNull(wLogs.find(w.getWorkLogId()));
  }

  @Test(expected = Exception.class)
  public void shouldNotBeAbleToSaveOverlappingWorkLogFromSameUser() throws Exception {
    
    User u = util.insertUser();
    WorkLog w1 = util.insertWorkLog(u);
    WorkLog w2 = util.insertWorkLog(u);
    // System.out.printf("%s\n%s%", w1,w2);
    w1.setWorkLogStart(new Date());
    w2.setWorkLogStart(new Date());
  }

  @Test(expected = Exception.class)
  public void shouldNotBeAbletoSaveNegativeInterval() throws Exception {

    WorkLog w = util.insertWorkLog();

    Date t1 = new Date();
    t1.setTime(System.currentTimeMillis()+1000);

    Date t2 = new Date();
    t2.setTime(System.currentTimeMillis()-1000);

    w.setWorkLogStart(t1);
    w.setWorkLogFinish(t2);
    
    wLogs.save(w);

  }

}
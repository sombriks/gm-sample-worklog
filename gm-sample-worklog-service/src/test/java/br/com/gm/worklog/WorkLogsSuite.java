package br.com.gm.worklog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gm.worklog.business.WorkLogs;
import br.com.gm.worklog.business.Users;

import br.com.gm.worklog.model.LogStatus;
import br.com.gm.worklog.model.WorkLog;
import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkLogsSuite {

  @Autowired
  private WorkLogs wLogs;

  @Autowired
  private Users users;

  @Autowired
  private TestUtil util;

  @Test 
  public void shouldSaveOneWorkLog() throws Exception {

    WorkLog w = util.insertWorkLog();

    assertNotNull(w.getWorkLogId());

    wLogs.del(w.getWorkLogId());

  }

}
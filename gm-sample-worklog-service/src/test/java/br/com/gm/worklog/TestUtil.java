package br.com.gm.worklog;

import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.gm.worklog.business.EventLogs;
import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.business.WorkLogs;
import br.com.gm.worklog.model.EventLog;
import br.com.gm.worklog.model.EventType;
import br.com.gm.worklog.model.LogStatus;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;
import br.com.gm.worklog.model.WorkLog;
import java.util.Date;

@Component
public class TestUtil {

  @Autowired
  private Users users;

  @Autowired
  private WorkLogs wLogs;

  @Autowired
  private EventLogs evLogs;

  public User insertUser(String login) {
    if (login == null)
      login = "foo" + System.currentTimeMillis();
    User u = new User();
    u.setUserLogin(login);
    u.setUserName("Mr. Foo Bar");
    u.setUserHash(DigestUtils.md5Hex("e1e2e3e4"));
    u = users.save(u);
    return u;
  }

  public User insertUser() {
    return insertUser(null);
  }

  public WorkLog insertWorkLog(User u, int jump) {

    if (u == null)
      u = insertUser();
    VwUser v = users.find(u.getUserId());

    LogStatus s = new LogStatus();
    s.setLogStatusId(1l);

    WorkLog w = new WorkLog();
    w.setStatus(s);
    w.setUser(v);
    // avoid worklog interval overlap

    Date t1 = new Date();
    t1.setTime((2 * jump * 3600000) + System.currentTimeMillis() - 3599000);

    Date t2 = new Date();
    t2.setTime((2 * jump * 3600000) + System.currentTimeMillis() + 3600000);

    w.setWorkLogStart(t1);
    w.setWorkLogFinish(t2);

    w = wLogs.save(w);

    return w;
  }

  public WorkLog insertWorkLog() {
    return insertWorkLog(null, 0);
  }

  public WorkLog insertWorkLog(User u) {
    return insertWorkLog(u, 0);
  }

  public EventLog insertEventLog(VwUser vu) {

    EventLog ev = new EventLog();

    EventType et = new EventType();
    et.setEventTypeId(1l);
    ev.setType(et);

    if (vu == null) {
      User u = insertUser();
      vu = users.find(u.getUserId());
    }
    ev.setUser(vu);

    ev.setEventLogDescription(vu.toString());

    ev = evLogs.save(ev);

    return ev;
  }

  public EventLog insertEventLog() {
    return insertEventLog(null);
  }

}
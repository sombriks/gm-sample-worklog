package br.com.gm.worklog;

import javax.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.worklog.resource.UserResource;
import br.com.gm.worklog.resource.WorkLogResource;
import br.com.gm.worklog.resource.EventLogResource;


@RestController
@SpringBootApplication
public class EntryPoint {

  @Autowired
  private Environment env;

  @Autowired
  private UserResource user;

  @Autowired
  private WorkLogResource workLog;

  @Autowired
  private EventLogResource eventLog;

  @RequestMapping("/user")
  public UserResource getUser() {
    return user;
  }

  @RequestMapping("/worklog")
  public WorkLogResource getWorkLog() {
    return workLog;
  }

  @RequestMapping("/eventlog")
  public EventLogResource getEventLog() {
    return eventLog;
  }

  @RequestMapping("/status")
  public String status() {
    return "ONLINE";
  }

  @PostConstruct
  public void initDb() {
    System.out.println(env.getProperty("app.name"));
    Flyway flyway = new Flyway();
    String dbUrl = env.getProperty("database.url");
    String dbUsername = env.getProperty("database.username");
    String dbPassword = env.getProperty("database.password");
    flyway.setDataSource(dbUrl, dbUsername , dbPassword);
    flyway.migrate();
  }

  public static void main(String... args) {
    SpringApplication.run(EntryPoint.class, args);
  }
}
package br.com.gm.worklog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.gm.worklog.business.EventLogs;
import br.com.gm.worklog.business.WorkLogs;
import br.com.gm.worklog.model.WorkLog;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/worklog")
public class WorkLogResource {

  @Autowired
  private WorkLogs workLogs;

  @Autowired
  private EventLogs events;

  // anyone remember the "dona florinda" pattern?
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<WorkLog> listbyUser(@RequestParam("userId") Long userId,
      @RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    return workLogs.listbyUser(userId, start, size);
  }

  @RequestMapping(value = "/{workLogId}", method = RequestMethod.GET)
  public WorkLog find(@PathVariable("workLogId") Long workLogId) {
    return workLogs.find(workLogId);
  }


  @RequestMapping(value = "", method = { RequestMethod.POST })
  public WorkLog save(@RequestBody WorkLog workLog) {
    workLog = workLogs.save(workLog);
    events.saveWorkLogCreation(workLog);
    return workLog;
  }
}
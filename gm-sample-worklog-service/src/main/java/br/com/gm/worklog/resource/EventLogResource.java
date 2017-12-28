package br.com.gm.worklog.resource;

import br.com.gm.worklog.business.EventLogs;
import br.com.gm.worklog.model.EventLog;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/eventlog")
public class EventLogResource {

  @Autowired
  private EventLogs events;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<EventLog> listEvents(@RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    return events.listbyUser(start, size);
  }
}
package br.com.gm.worklog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.gm.worklog.business.EventLogs;
import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

  @Autowired
  private Users users;

  @Autowired
  private EventLogs events;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<VwUser> listByName(@RequestParam("query") String query,
      @RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    return users.listByName(query, start, size);
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public VwUser find(@PathVariable("userId") Long userId) {
    return users.find(userId);
  }

  // post for insert, put for update, however jpa makes it irrelevant
  @RequestMapping(value = "", method = RequestMethod.POST)
  public VwUser insert(User user) {
    user = users.save(user);
    VwUser author = users.find(user.getUserId());// TODO get from authentication channels
    events.saveUserCreation(user, author); // POST -> creation, PUT -> modification
    return users.find(user.getUserId());
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  public VwUser update(User user) {
    user = users.save(user);
    VwUser author = users.find(user.getUserId());// TODO get from authentication channels
    events.saveUserModification(user, author); 
    return users.find(user.getUserId());
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
  public String del(@PathVariable("userId") Long userId) {
    VwUser author = users.find(userId);// TODO get from authentication channels
    users.del(userId);
    events.seveUserDeletion(userId, author);
    return "OK";
  }

}
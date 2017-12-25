package br.com.gm.worklog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.model.VwUser;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

  @Autowired
  private Users users;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<VwUser> list(@RequestParam("query") String query,
      @RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    return users.list(query, start, size);
  }

}
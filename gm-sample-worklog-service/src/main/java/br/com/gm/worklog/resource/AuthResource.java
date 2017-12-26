package br.com.gm.worklog.resource;

import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.model.User;
import br.com.gm.worklog.model.VwUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

/*
 * For simplicity we are both Authorization Server and Resource server. 
 * https://tools.ietf.org/html/rfc6750#page-4 
 */
@RestController
@RequestMapping("/auth")
public class AuthResource {

  @Autowired
  private Users users;

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public VwUser register(@RequestBody User u) {
    u = users.save(u);
    VwUser vu = users.find(u.getUserId());
    return vu;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestBody User u) {
    
    u = users.findByLoginAndHash(u.getUserLogin(), u.getUserHash());
    
    // TODO it would be nicer if we generate a brand new token and stored it 
    // so every new login could invalidade the previous, but we can polish it later
    Key key = new SecretKeySpec(u.getUserHash().getBytes(), SignatureAlgorithm.HS256.getJcaName());

    String compactJws = Jwts.builder().setSubject(u.getUserLogin())
        .signWith(SignatureAlgorithm.HS256, key).compact();

    // green on https://jwt.io/
    return compactJws;
  }
}
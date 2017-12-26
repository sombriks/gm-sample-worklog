package br.com.gm.worklog.security;

import br.com.gm.worklog.business.Users;
import br.com.gm.worklog.model.User;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

// https://stackoverflow.com/a/47846023/420096
@WebFilter({ "/user", "/worklog" })
public class SecFilter extends OncePerRequestFilter {

  @Autowired
  private Users users;

  private List<String> verbs = Arrays.asList(new String[] { "POST", "PUT", "DELETE" });

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (verbs.contains(request.getMethod())) {
      String header = request.getHeader("Authorization");
      if (header == null) {
        response.setStatus(403);
        response.getWriter().write("You must provide the Authorization header");
      } else {
        if (header.indexOf("Bearer") > -1) {

          String token = header.replace("Bearer ", "");

          String userLogin = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

          System.out.println("**** "+userLogin+" ****");
          // **** {"sub":"joe"} ****

          User u = users.findbyLogin(userLogin);

          Key key = new SecretKeySpec(u.getUserHash().getBytes(), SignatureAlgorithm.HS256.getJcaName());

          String trust = Jwts.parser().setSigningKey(key).parseClaimsJws(header).getBody().getSubject();
        
          filterChain.doFilter(request, response);
        } else {
          // response.setStatus(418);
          response.setStatus(400); //
          response.getWriter().write("Malformed Authorization header");
        }
      }
    }
  }
}
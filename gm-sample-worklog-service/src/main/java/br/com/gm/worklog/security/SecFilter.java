package br.com.gm.worklog.security;

import br.com.gm.worklog.business.Users;
import java.io.IOException;
import java.util.Arrays;
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

  private List<String> verbs = Arrays.asList(new String[] { "POST", "PUT" });

  @Autowired
  private Users users;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (true || verbs.contains(request.getMethod())) {
      String header = request.getHeader("Authorization");
      if (header == null) {
        response.setStatus(401);
        return;
      } else {
        Key key = new SecretKeySpec("e1e2e3e4".getBytes(), SignatureAlgorithm.HS256.getJcaName());
        String userLogin = Jwts.parser().setSigningKey(key).parseClaimsJws(header).getBody().getSubject();
        System.out.println(userLogin);
      }

    }
    filterChain.doFilter(request, response);
  }

}
package br.com.gm.worklog.security;

import br.com.gm.worklog.business.Users;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

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
      if(header == null){
        response.setStatus(401);
        return;
      }else {
        
      }
    }
    filterChain.doFilter(request, response);
  }

}
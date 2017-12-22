package br.com.gm.worklog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.worklog.resource.UserResource;

@RestController
@SpringBootApplication
public class EntryPoint {

  @Autowired
  private UserResource user;


  @RequestMapping("/user")
  public UserResource getUser() {
    return user;
  }

  @RequestMapping("/status")
  public String status() {return "ONLINE"; }

  public static void main(String ...args) { 
    System.out.println("Alive and kicking!");
    SpringApplication.run(EntryPoint.class, args); 

  }
}
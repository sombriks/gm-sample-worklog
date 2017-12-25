package br.com.gm.worklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EntryPoint {

  @RequestMapping("/status")
  public String status() {
    return "ONLINE";
  }

  public static void main(String... args) {
    SpringApplication.run(EntryPoint.class, args);
  }
}
package br.com.gm.worklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EntryPoint {

  public static void main(String... args) {
    SpringApplication.run(EntryPoint.class, args);
  }
}
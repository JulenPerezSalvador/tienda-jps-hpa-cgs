package es.iesclaradelrey.da2d1a.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Esta parte le esta indicando desde que archivo empiza a leer Spring-boot ya que estan en distintos modulos
@SpringBootApplication(scanBasePackages = "es.iesclaradelrey.da2d1a")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
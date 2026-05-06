package es.iesclaradelrey.da2d1a.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "es.iesclaradelrey.da2d1a")
@EntityScan(basePackages = "es.iesclaradelrey.da2d1a.common.entities")
@EnableJpaRepositories(basePackages = "es.iesclaradelrey.da2d1a.common.repositories")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// The @SpringBootApplication annotation is a convenience annotation that adds all of the following:
// @Configuration: Tags the class as a source of bean definitions for the application context.
// @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
// @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.
@SpringBootApplication
// The @EnableJpaRepositories annotation is used to enable JPA repositories. This is a more advanced feature of Spring Data JPA, which allows you to manipulate entities with methods other than the standard set provided by the JpaRepository.
@EnableJpaRepositories("com.example.accessingdatamysql")
public class AccessingDataMySQLApplication {

  public static void main(String[] args) {
    // The SpringApplication.run() method launches an application. Here we are passing the AccessingDataMySQLApplication.class as an argument to the run method.
    SpringApplication.run(AccessingDataMySQLApplication.class, args);
  }

  // The @Bean annotation tells Spring that this method will return an object that should be registered as a bean in the Spring application context.
  @Bean
  public MainController mainController(UserRepository userRepository) {
    return new MainController(userRepository);
  }
}
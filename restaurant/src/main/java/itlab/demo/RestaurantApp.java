package itlab.demo;

import itlab.demo.model.entity.Role;
import itlab.demo.model.entity.User;
import itlab.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class RestaurantApp {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApp.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService)
    {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "Danila", "beda", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Meksim", "beer", "1", new ArrayList<>()));
            userService.saveUser(new User(null, "Vova", "io", "test", new ArrayList<>()));

            userService.addRoleToUser("beda", "ROLE_USER");
            userService.addRoleToUser("beer", "ROLE_ADMIN");
            userService.addRoleToUser("io", "ROLE_ADMIN");
        };
    }
}

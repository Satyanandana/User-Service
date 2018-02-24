package com.service.user;

import com.service.user.dao.UserRepository;
import com.service.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Stream;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.service.user.dao"})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Bean
	CommandLineRunner getRunner(UserRepository userRepository){
		return strings -> {
			Stream.of("Bhavya","Nandu").forEach(item -> userRepository.save(new User(item)));
		};
	}
}

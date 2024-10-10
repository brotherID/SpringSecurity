package procheck.cih.support;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import procheck.cih.support.service.AccountService;

@SpringBootApplication
public class SupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportApplication.class, args);
	}
	
	
	@Bean
    PasswordEncoder passwordEncoder() {
    	
    	return new BCryptPasswordEncoder();
		
	}
	
	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder  passwordEncoder = passwordEncoder();
		return args -> {
			if (!jdbcUserDetailsManager.userExists("user1")) {
				jdbcUserDetailsManager.createUser(User.withUsername("user1")
						.password(passwordEncoder.encode("1234"))
						.roles("USER").build());
			}
			 if (!jdbcUserDetailsManager.userExists("admin")) {
				 jdbcUserDetailsManager.createUser(User.withUsername("admin")
						 .password(passwordEncoder.encode("1234"))
						 .roles("ADMIN").build());
			 }
			
		};
		
	}
	
	//@Bean
	CommandLineRunner commandLineRunner(AccountService accountService) {
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.addNewUser("user1", "1234", "1234");
			accountService.addNewUser("user2", "1234", "1234");
			accountService.addNewUser("admin", "1234", "1234");
			
			accountService.addRoleToUser("user1", "USER");
			accountService.addRoleToUser("user2", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
			
		};
		
		
		
	}

}

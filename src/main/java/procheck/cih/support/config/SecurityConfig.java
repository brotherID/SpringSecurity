package procheck.cih.support.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import procheck.cih.support.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
	
	
	private PasswordEncoder passwordEncoder;
	private UserDetailServiceImpl userDetailServiceImpl;
	
	
	
	
	//@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);  // Gestion des utilisateurs via JDBC
    }

	
	
	//@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		return new InMemoryUserDetailsManager(
				User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
				User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
				User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build()
				);
	}
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.
    	formLogin();	
//    	http.authorizeHttpRequests()
//         .antMatchers("/admin/**").hasRole("ADMIN");
//    	http.authorizeHttpRequests()
//        .antMatchers("/user/**").hasRole("USER");
    	
        http
            .authorizeHttpRequests().anyRequest().authenticated();
        http.userDetailsService(userDetailServiceImpl);
        return http.build();
    }

}

package ua.edu.ukma.event_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session ->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

}

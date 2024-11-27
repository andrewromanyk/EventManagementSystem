package ua.edu.ukma.event_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.edu.ukma.event_management_system.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	private JwtFilter jwtFilter;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setJwtFilter(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				.authorizeHttpRequests(request -> request
						.requestMatchers("/login").permitAll()
						.requestMatchers("/tologout").permitAll()
						.requestMatchers("/FAQ.html").permitAll()
						.requestMatchers("/styles/*.css").permitAll()
						.requestMatchers("/*.png").permitAll()
						.requestMatchers("/register/**").permitAll()
						.requestMatchers("/register").permitAll()
						.requestMatchers("/myprofile").hasAuthority("USER")
						.requestMatchers("/building").hasAuthority("ORGANIZER")
						.requestMatchers("/building/**").hasAuthority("USER")
						.requestMatchers(HttpMethod.GET, "/api/building/**").hasAuthority("USER")
						.requestMatchers(HttpMethod.GET, "/event/").permitAll()
						.requestMatchers(HttpMethod.GET, "/event/*").permitAll()
						.requestMatchers(HttpMethod.GET, "/event/**").hasAuthority("ORGANIZER")
						.requestMatchers("/event/**").hasAuthority("ORGANIZER")
						.requestMatchers("/user").hasAuthority("ORGANIZER")
						.requestMatchers("/user/**").hasAuthority("USER")
						.requestMatchers( "/ticket/**").hasAuthority("USER")
						.requestMatchers("/main").permitAll()
						.requestMatchers("/manage/cache").hasAuthority("ADMIN")
						.requestMatchers("/manage/**").hasAuthority("USER")
						.requestMatchers("/api/**").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET, "/ticket/**").hasAuthority("USER")
						.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
				.exceptionHandling(exception -> exception
						.authenticationEntryPoint((request, response, authException) ->
								response.sendRedirect("/login")))
				.sessionManagement(session ->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.logout(LogoutConfigurer::permitAll)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}

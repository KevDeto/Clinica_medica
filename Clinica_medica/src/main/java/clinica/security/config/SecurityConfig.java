package clinica.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import clinica.security.jwt.JwtAuthenticationFilter;
import clinica.security.jwt.JwtAuthorizationFilter;
import clinica.security.jwt.JwtUtils;

@Configuration
public class SecurityConfig {
	private JwtUtils jwtUtils;
	private  JwtAuthorizationFilter jwtAuthorizationFilter;
	
	
	public SecurityConfig(JwtUtils jwtUtils, JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtUtils = jwtUtils;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}
	
	//la sugerencia me dice que puede ser un metodo que sea visible en todo el paquete
	//es decir, no debe llevar ni default, ni private, ni public, ni protected
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthenticationManager authenticationManager) throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		return httpSecurity.csrf(auth -> auth.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/v1/user").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(jwtAuthenticationFilter) //primero valida el token
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // es mas dificl de desencriptar
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();		
	}
}

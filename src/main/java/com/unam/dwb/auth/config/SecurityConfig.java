package com.unam.dwb.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unam.dwb.auth.service.impl.DefaultUserAuthentication;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
		.csrf(csrf -> csrf.disable())
		.formLogin(form -> form.disable())
		.httpBasic(httpBasic -> httpBasic.disable());
			
		return http.build();
	}
	
	@Bean
    AuthenticationManager authenticationManager(DefaultUserAuthentication defaultUserAuthentication) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(defaultUserAuthentication);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	/**
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuario/**").hasAnyRole("CUSTOMER", "ADMIN") 
                .requestMatchers("/user/**").hasRole("ADMIN") 
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    **/

}

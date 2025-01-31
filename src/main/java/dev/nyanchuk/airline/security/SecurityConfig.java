package dev.nyanchuk.airline.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow all users to register and upload their profile images
                .requestMatchers("/api/auth/register", "/api/auth/login", "/api/profile/upload").permitAll()
                // Allow ADMIN to perform CRUD operations on airports and flights
                .requestMatchers("/api/airports/**").hasRole("ADMIN")
                .requestMatchers("/api/flights/**").hasRole("ADMIN")
                // Allow ADMIN to view reservation summaries and user reservation history
                .requestMatchers("/api/reservations/summary").hasRole("ADMIN")
                .requestMatchers("/api/reservations/history/**").hasRole("ADMIN")
                // Allow authenticated users to view their own reservations
                .requestMatchers("/api/reservations/mine").hasRole("USER")
                // All other requests need to be authenticated
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Configure HTTP Basic authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        userDetailsService.createUser(
            org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build()
        );

        userDetailsService.createUser(
            org.springframework.security.core.userdetails.User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build()
        );

        return userDetailsService;
    }
}
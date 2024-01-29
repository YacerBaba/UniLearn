package com.yacer.unilearn.security;

import com.yacer.unilearn.authentication.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .formLogin(loginConfigurer -> {
                })
                .authorizeHttpRequests(
                        requestMatcher -> {
                            requestMatcher
                                    .requestMatchers("/api/auth/**")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                ).sessionManagement(sessionManagementConfigurer -> {
                    sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        // AuthenticationProvider class in Spring Security is responsible for authenticating a user.
        // The authenticate method performs the actual authentication based on the provided credentials.
        // The supports method indicates which authentication classes are supported by this provider.
        // The DaoAuthenticationProvider is designed to work with a user details service
        // This service retrieves user details (like username, password, and authorities) from a data store.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // AuthenticationManager is responsible for authenticating a user based on the provided credentials
        // it doesn't perform the authentication itself but delegates this task to one or more registered AuthenticationProvider
        // Each AuthenticationProvider is responsible for a specific type of authentication, such as database authentication, LDAP authentication, or custom authentication logic.
        // The AuthenticationManager iterates through its list of providers, asking each one to authenticate the user.
        return configuration.getAuthenticationManager();
    }
}

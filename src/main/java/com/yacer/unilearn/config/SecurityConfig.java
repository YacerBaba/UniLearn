package com.yacer.unilearn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yacer.unilearn.auth.pojos.CustomEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
                    loginConfigurer.disable();
                })
                .authorizeHttpRequests(
                        requestMatcher -> requestMatcher
                                .requestMatchers("/api/auth/**", "/api/docs/**")
                                .permitAll()
                                // Student controller
                                .requestMatchers(HttpMethod.GET,
                                        "/api/students/",
                                        "/api/students/{level}",
                                        "/api/students/profile/{id}").hasAuthority("view_student")
                                .requestMatchers(HttpMethod.POST, "/api/students/").hasAuthority("add_student")
                                .requestMatchers(HttpMethod.PUT, "/api/students/").hasAuthority("update_student")
                                .requestMatchers(HttpMethod.DELETE, "/api/students/{id}").hasAuthority("delete_student")
                                // Teacher controller
                                .requestMatchers(HttpMethod.GET,
                                        "/api/teachers/", "/api/teachers/profile/{id}").hasAuthority("view_teacher")
                                .requestMatchers(HttpMethod.POST, "/api/teachers/").hasAuthority("add_teacher")
                                .requestMatchers(HttpMethod.PUT, "/api/teachers/").hasAuthority("update_teacher")
                                .requestMatchers(HttpMethod.DELETE, "/api/teachers/profile/{id}").hasAuthority("delete_teacher")
                                // Level controller
                                .requestMatchers(HttpMethod.GET, "/api/levels/").hasAuthority("view_level")
                                // Department controller
                                .requestMatchers(HttpMethod.GET, "/api/departments/").hasAuthority("view_department")
                                // Module controller
                                .requestMatchers(HttpMethod.GET, "/api/modules/").hasAuthority("view_module")

                                .anyRequest()
                                .denyAll()
                ).sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(configurer -> {
                    configurer.authenticationEntryPoint(new CustomEntryPoint(new ObjectMapper()));
                });
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        // AuthenticationProvider class is responsible for authenticating a user.
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

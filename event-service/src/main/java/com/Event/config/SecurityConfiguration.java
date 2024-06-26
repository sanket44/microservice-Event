package com.Event.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
//    private final JwtAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//    @Bean
//    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/api/v1/home/**").permitAll()
//                .requestMatchers("/api/v1/user/**").hasAuthority("USER")
//                .requestMatchers("/api/v1/vendor/**").hasAuthority("VENDOR")
//                .requestMatchers("/api/v1/order/**").hasAuthority("USER")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain springSecurityChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(ex -> ex.anyRequest().authenticated()).oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

}

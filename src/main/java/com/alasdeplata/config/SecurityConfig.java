package com.alasdeplata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alasdeplata.common.JwtUtils;
import com.alasdeplata.config.filter.JwtTokenValidator;
import com.alasdeplata.repository.UserRepository;
import com.alasdeplata.services.impl.UserDetailServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint((request, response, authException) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar los Endpoints Publicos
                    http.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                    http.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/destinations/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/flights/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/airlines/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/airplanes/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/flight-prices/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/seats/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/additional-services/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/reservation-additional-services/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/flight-additional-services/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/payments/**").authenticated();


                    http.requestMatchers(HttpMethod.GET, "/api/v1/permissions/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/permissions/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/permissions/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/permissions/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN", "USER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll();  // temporalmente
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/airlines/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/airlines/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/airlines/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/flights/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/flights/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/flights/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/flight-prices/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/flight-prices/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/flight-prices/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/destinations/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/destinations/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/destinations/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/passengers/**").hasRole("ADMIN");


                    http.requestMatchers(HttpMethod.POST, "/api/v1/payments/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/payments/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/payments/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/seats/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/seats/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/seats/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/reservations/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/reservations/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/reservations/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/reservations/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/reservation-passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/reservation-passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/reservation-passengers/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/reservation-passengers/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/airplanes/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/airplanes/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/airplanes/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/contacto").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/flights/to/**").permitAll();

                    http.requestMatchers(HttpMethod.POST, "/api/chat").permitAll();

                    http.requestMatchers(HttpMethod.GET, "/api/v1/auth/me").authenticated();






                    // Configurar los endpoints privados
                    // http.requestMatchers(HttpMethod.GET,
                    // "/api/v1/products/**").hasAnyRole("ADMIN", "USER", "INVITED");
                    // http.requestMatchers(HttpMethod.POST,
                    // "/api/v1/products/**").hasAnyRole("ADMIN", "USER");
                    // http.requestMatchers(HttpMethod.PUT,
                    // "/api/v1/products/**").hasAnyRole("ADMIN", "USER");
                    // http.requestMatchers(HttpMethod.DELETE,
                    // "/api/v1/products/**").hasAnyRole("ADMIN", "USER");

                    // Configurar el resto de endpoints - no especificados
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils, userRepository), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}

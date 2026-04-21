package com.apigateway.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class JwtAuthConfig {

//    @Autowired
//    RequestResponseLoggingFilter authFilter;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
//        System.out.println("path1=");
//        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                        .authorizeExchange(ex->
//                                ex.anyExchange().permitAll())
////                .oauth2ResourceServer(oauth->oauth.())
//                .addFilterBefore(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        exchanges -> exchanges.pathMatchers("/actuator/*").permitAll()
                        .anyExchange().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.disable())
                .build();
    }
}

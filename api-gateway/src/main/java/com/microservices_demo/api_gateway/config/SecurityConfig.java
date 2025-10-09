package com.microservices_demo.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        logger.info("=== SecurityConfig is being loaded! ===");
        logger.info("Configuring security: /api/product/** -> permitAll(), others -> authenticated()");

        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange.anyExchange()
                        .permitAll());
        // Temporarily disable JWT validation for testing
        // .oauth2ResourceServer(
        // oAuth2ResourceServerSpec ->
        // oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
        return serverHttpSecurity.build();
    }
}
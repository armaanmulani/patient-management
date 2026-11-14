package com.pm.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class ReferrerPolicyConfig {

    @Bean
    public WebFilter referrerPolicyFilter() {
        return (exchange, chain) -> {
            exchange.getResponse().beforeCommit(() -> {
                exchange.getResponse().getHeaders()
                        .set("Referrer-Policy", "no-referrer");
                return reactor.core.publisher.Mono.empty();
            });

            return chain.filter(exchange);
        };
    }
}
package com.toy.project.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditListener();
    }
}

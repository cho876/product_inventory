package com.toy.project.product.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuditListener implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String requestUri = "SYSTEM";

        return Optional.of(requestUri);
    }
}

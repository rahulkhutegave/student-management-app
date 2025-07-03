package com.student.main.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Value("${multitenancy.tenant.default-tenant}")
    private String defaultTenant;

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        if (StringUtils.hasText(tenantId)) {
            return tenantId;
        }
        // Return the default tenant if no tenant found
        return defaultTenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
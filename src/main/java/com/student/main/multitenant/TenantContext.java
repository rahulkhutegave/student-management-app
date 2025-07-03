package com.student.main.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class TenantContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantContext.class);
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenantId) {
        LOGGER.debug("Setting tenant to {}", tenantId);
        currentTenant.set(tenantId);
    }

    public static void clear() {
        currentTenant.remove();
    }
}

package com.student.main.multitenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Check if the user is authenticated
        if (request.getRequestURI().contains("/api/admin"))
            TenantContext.setCurrentTenant("public");
        else if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails instanceof com.student.main.security.services.UserDetailsImpl) {
                String email = ((com.student.main.security.services.UserDetailsImpl) userDetails).getEmail();
                String tenantId = email.substring(email.indexOf("@") + 1, email.lastIndexOf("."));
                logger.debug("Schema name {}", tenantId);
                TenantContext.setCurrentTenant(tenantId);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Clear the tenant context after the request is handled
        TenantContext.clear();
    }

}

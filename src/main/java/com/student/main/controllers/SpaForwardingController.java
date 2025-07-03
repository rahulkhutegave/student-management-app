package com.student.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaForwardingController {

    /**
     * Forwards any path that does not contain a dot ('.') to the root.
     * This is a simple way to distinguish between frontend routes (e.g., /products)
     * and requests for static assets (e.g., /static/js/main.js).
     *
     * It also ignores paths under /api/ to avoid interfering with backend controllers.
     *
     * @return The forward instruction to the root path.
     */
    @RequestMapping(value = { "/{path:[^\\.]*}" })
    public String forward() {
        // Forward to the root path ('/') where index.html is served.
        return "forward:/";
    }
}
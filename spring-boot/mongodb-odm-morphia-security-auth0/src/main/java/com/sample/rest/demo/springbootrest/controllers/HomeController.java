package com.sample.rest.demo.springbootrest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final Principal principal) {
        logger.info("Home page");
        if (principal == null) {
            return "redirect:/logout";
        }

        System.out.println("\n");
        System.out.println("[name]: " + principal.getName());
        System.out.println("[hashCode]: " + principal.hashCode());
        System.out.println("[toString]: " + principal.toString());
        System.out.println("\n");

        model.put("name", principal.getName());
        model.put("hashCode", principal.hashCode());
        model.put("toString", principal.toString());
        model.put("userId", principal);
        return "home";
    }

}

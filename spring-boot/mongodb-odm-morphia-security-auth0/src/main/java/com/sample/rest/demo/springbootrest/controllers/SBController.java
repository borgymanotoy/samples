package com.sample.rest.demo.springbootrest.controllers;


import com.auth0.AuthenticationController;
import com.sample.rest.demo.springbootrest.configs.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class SBController {

    @Autowired
    private AuthenticationController controller;

    @Autowired
    private AppConfig appConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final HttpServletRequest req) {

        logger.debug("Performing login");

        System.out.println("[scheme]: " + req.getScheme());
        System.out.println("[server-name]: " + req.getServerName());
        System.out.println("[port]: " + req.getServerPort());
        System.out.println("[domain]: " + appConfig.getDomain());


        String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/callback";
        String authorizeUrl = controller.buildAuthorizeUrl(req, redirectUri)
                .withAudience(String.format("https://%s/userinfo", appConfig.getDomain()))
                .build();

        System.out.println("[authorize-url]: " + authorizeUrl);

        return "redirect:" + authorizeUrl;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
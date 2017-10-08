package com.sample.rest.demo.springbootrest.controllers;

import com.auth0.AuthenticationController;
import com.sample.rest.demo.springbootrest.configs.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationController controller;
    @Autowired
    private AppConfig appConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

/*    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(final HttpServletRequest req) {
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
*/

}

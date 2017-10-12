package com.sample.rest.demo.springbootrest.controllers;


import com.auth0.AuthenticationController;
import com.sample.rest.demo.springbootrest.configs.AppConfig;
import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.RoleRepository;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import com.sample.rest.demo.springbootrest.services.RoleService;
import com.sample.rest.demo.springbootrest.services.SecurityService;
import com.sample.rest.demo.springbootrest.services.UserService;
import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Controller
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;



    @GetMapping(value = "/")
    public String index() {
        return "login";
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) boolean hasError, ModelMap map) {
        String msg = null;
        if(hasError){
            msg = "Invalid user/password!";
            map.addAttribute("hasError", hasError);
            map.addAttribute("msg", msg);
        }

        return "login";
    }

    @GetMapping(value = "/register")
    public String register(@RequestParam(value = "error", required = false) boolean hasError, ModelMap map) {
        List<Role> availableRoles = this.roleService.list();
        map.addAttribute("availableRoles", availableRoles);

        String msg = null;
        if(hasError){
            msg = "Unable to register user!";
            map.addAttribute("hasError", hasError);
            map.addAttribute("msg", msg);
        }

        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                               @RequestParam(value = "password", required = false, defaultValue = "") String password,
                               @RequestParam(value = "verifyPassword", required = false, defaultValue = "") String verifyPassword,
                               ModelMap map) {

        System.out.println("\n\n");
        System.out.println("GET-MAPPING-REGISTER");
        System.out.println("-----------------------------------------------");
        System.out.println("[username]: " + username);
        System.out.println("[password]: " + password);
        System.out.println("[verifyPassword]: " + verifyPassword);
        System.out.println("-----------------------------------------------");
        System.out.println("\n\n");

        MessageState msgState = new MessageState();
        if(0 < username.trim().length() && 0 < password.trim().length() && 0 < verifyPassword.trim().length()){
            if(verifyPassword.equalsIgnoreCase(password)){
                User user = new User(username);
                user.setPassword(password);
                user.getRoles().add(this.roleService.findByCode("USER"));

                System.out.println("\n\n");
                System.out.println("[USER]: " + user.toString());
                System.out.println("\n\n");

                if(this.userService.save(user)){
                    msgState.setCode("OK");
                    msgState.setMessage("Registration Successful.");
                }
                else {
                    msgState.setCode("ERROR");
                    msgState.setMessage("Registration failed, user already exists!");
                }
            }
            else {
                msgState.setCode("ERROR");
                msgState.setMessage("Passwords does not match!");
            }
        }
        else {
            msgState.setCode("ERROR");
            msgState.setMessage("Please fill-up all the registration fields.");
        }

        map.addAttribute("status", msgState);
        if("ERROR".equalsIgnoreCase(msgState.getCode())){

            System.out.println("\n\n");
            System.out.println("-----------------------");
            System.out.println("O.M.G. NAAY ERROR!!!");
            System.out.println("-----------------------");
            System.out.println("\n\n");

            map.addAttribute("username", username);
            map.addAttribute("password", password);
            map.addAttribute("verifyPassword", verifyPassword);
            map.addAttribute("hasError", true);
            map.addAttribute("statusMessage", msgState.getMessage());

            return null;
        }
        else if("OK".equalsIgnoreCase(msgState.getCode())) {
            if(null == securityService.findLoggedInUsername()) {
                if (securityService.autologin(username, password))
                    return "redirect:/home";
                else {
                    map.addAttribute("username", username);
                    map.addAttribute("password", password);
                    map.addAttribute("verifyPassword", verifyPassword);
                    map.addAttribute("hasError", true);
                    map.addAttribute("statusMessage", "Authentication failed!");
                    return null;
                }
            }
        }

        return null;
    }

    @GetMapping(value = "/home")
    public String home(HttpSession session, Authentication authentication, ModelMap model) {
        String username = authentication.getName();
        String sessionId = session.getId();

        System.out.println("[username]: " + username);
        System.out.println("[session-id]: " + sessionId);

        model.addAttribute("sessionId", sessionId);
        model.addAttribute("userInfo", username);

        return "home";
    }

    @GetMapping(value = "/profile")
    public String profile(Authentication authentication, ModelMap model) {
        User user = this.userService.findByUsername(authentication.getName());
        model.addAttribute("userInfo", user);
        return "profile";
    }

}
package com.borgymanotoy.samples.jwt.springbootjwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by borgymanotoy on 6/20/17.
 */

@RestController
public class UserController {

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    @RequestMapping("/users")
    public @ResponseBody
    String getUsers() {
        return "{\"users\":[{\"firstname\":\"Borgy\", \"lastname\":\"Manotoy\"}," +
                "{\"firstname\":\"Laarnie\",\"lastname\":\"Salip Ahmad\"}]}";
    }

}

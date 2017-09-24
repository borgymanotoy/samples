package com.sample.rest.demo.springbootrest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestWebApp extends SpringBootRestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    private String content01 = "{\"username\": \"dummyUser01\",\"firstName\": \"Dummy\",\"lastName\": \"User01\",\"email\": \"dummyUser01@pogi.ph\",\"contactNo\": \"(0949) 993-0422\",\"password\": \"letmein123\"}";

    @SuppressWarnings("unused")
    private String content02 = "{}";

    @SuppressWarnings("unused")
    private String content03 = "{\"username\": \"dummyUser02\",\"firstName\": \"Dummy\"}";

    private String content04 = "{\"username\": \"dummyUser03\",\"firstName\": \"Dummy\",\"lastName\": \"User03\"}";
    private String updateContent = "{\"username\": \"dummyUser03\",\"firstName\": \"Dummy\",\"lastName\": \"User03\",\"email\": \"dummyUser03@pogi.ph\",\"contactNo\": \"(0949) 993-0422\",\"password\": \"dummyuser03\"}";
    private String searchUser = "dummyUser03";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testListUsers() throws Exception {
        mockMvc.perform(get("/api/listUsers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testAddUser() throws Exception {

        mockMvc.perform(post("/api/addUser")
                    .content(content04)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/api/getUser").param("username", searchUser))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(post("/api/updateUser")
                .content(updateContent)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveUser() throws Exception {
        mockMvc.perform(post("/api/removeUser").param("username", searchUser))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
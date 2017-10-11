package com.sample.rest.demo.springbootrest;

import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.MessageState;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebAppContactsTest extends SpringBootRestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static String testObjectId = null;
    @SuppressWarnings("unused")
    private String content01 = "{\"firstName\": \"Dummy\",\"lastName\": \"Contact01\",\"email\": \"dummyContact01@pogi.ph\",\"contactNo\": \"(0949) 993-0422\"}";

    @SuppressWarnings("unused")
    private String content02 = "{}";

    @SuppressWarnings("unused")
    private String content03 = "{\"firstName\": \"Dummy\"}";

    private String content04 = "{\"firstName\": \"Dummy\",\"lastName\": \"Contact03\"}";
    private String updateContent = "{\"firstName\": \"Dummy\",\"lastName\": \"Contact03\",\"email\": \"dummyContact03@pogi.ph\",\"contactNo\": \"(0949) 993-0422\"}";
    private String searchUser = "dummyContact03";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test10ListContact() throws Exception {
        mockMvc.perform(get("/api/listContact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void test11AddContact() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/api/addContact")
                .content(content04)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        if(null!=result){
            Gson gson = new Gson();
            MessageState msgState = gson.fromJson(result.getResponse().getContentAsString(), MessageState.class);
            testObjectId = msgState.getResultObjectId();
            System.out.println("\t[content]: " + testObjectId);
        }

    }

    @Test
    public void test12GetContact() throws Exception {
        mockMvc.perform(get("/api/getContact").param("id", testObjectId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test13UpdateContact() throws Exception {
        Contact contact = new Contact();
        contact.setId(new ObjectId(testObjectId));
        contact.setFirstName("Borgy");
        contact.setLastName("Manotoy");
        contact.setContactNo("(0949) 993-0422");
        contact.setEmail("borgymanotoy@pogi.ph");

        Gson gson = new Gson();
        String updateJson = gson.toJson(contact);

        mockMvc.perform(post("/api/updateContact")
                .content(updateJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void test14RemoveContact() throws Exception {
        mockMvc.perform(post("/api/removeContact").param("id", testObjectId))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
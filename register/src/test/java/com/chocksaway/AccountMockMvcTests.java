package com.chocksaway;

import com.chocksaway.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountMockMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected ObjectMapper mapper;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(Account.class);
        mapper = new ObjectMapper();
    }

//    @After
//    public void after() {
//        mongoTemplate.dropCollection(Account.class);
//    }


    @Test
    public void testPostToRegister() throws Exception {
        String content = mockMvc
                .perform(post("/register")
                        //.header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "username")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }


    @Test

    public void testAddJusticeLeagueMember() throws Exception {
        mapper = new ObjectMapper();

        Account account = new Account("miles", "password");

        mongoTemplate.save(account);

        String jsonContent = mapper.writeValueAsString(account);

        String response = mockMvc

                .perform(MockMvcRequestBuilders.post("/authenticate").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(jsonContent))

                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(response.contains(account.getUsername()));
    }
}
package com.chocksaway;


import com.chocksaway.config.MvcConfig;
import com.chocksaway.config.WebSecurityConfig;
import com.chocksaway.controller.AccountController;
import com.chocksaway.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@ContextConfiguration(classes={Application.class, MvcConfig.class, WebSecurityConfig.class})
public class AccountMockMvcTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountRepository accountRepository;


    @Test
    public void contact() throws Exception {
        mvc.perform(get("/register"))
                .andDo(print());
    }


    @Test
    public void testLoadRegistrationPage() throws Exception {
        this.mvc.perform(get("/register")).andExpect(status().isOk());
    }

    // todo need to get this working with CSRF tokens

    @Test
    public void testPostToRegister() throws Exception {
        String content = mvc
                .perform(post("/register")
                        //.header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "xxx")
                        .param("password", "xxx"))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
    }
}
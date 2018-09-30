/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chocksaway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Happy path
     * user exists and password matches
     *
     * Authenticate User
     *
     * @throws Exception
     */
    @Test
    public void testAuthenticateWithNewUser() throws Exception {
        this.mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"john\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("{\"id\": 2, \"username\": \"john\", \"status\": \"SUCCESS\"}"));
    }

    /**
     * Sad path
     * frank user does not exist
     *
     * Authenticate User
     *
     * @throws Exception - exception
     */
    @Test
    public void testAuthenticateWithNonExistingUser() throws Exception {
        this.mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"frank\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Sad path
     * john user exists, but password wrong
     *
     * Authenticate User
     *
     * @throws Exception - exception
     */
    @Test
    public void testAuthenticateWithWrongPassword() throws Exception {
        this.mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"john\",\n" +
                        "  \"password\": \"password123\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Sad path
     * john user exists, but password wrong
     *
     * Authenticate User
     *
     * @throws Exception - exception
     */
    @Test
    public void testAuthenticateNullUser() throws Exception {
        this.mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": null,\n" +
                        "  \"password\": \"password123\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Sad path
     * john user exists
     *
     * @throws Exception
     */
    @Test
    public void testAddNewValidUserAlreadyExists() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"john\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Happy Path
     * Add mark user does not exist
     *
     * @throws Exception
     */
    @Test
    public void testAddNewValidUserDoesNotExist() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"mark\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}

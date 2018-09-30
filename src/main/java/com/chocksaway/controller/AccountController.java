package com.chocksaway.controller;

import com.chocksaway.entity.Account;
import com.chocksaway.exception.UserAlreadyExistsException;
import com.chocksaway.exception.UserNotFoundException;
import com.chocksaway.exception.UserUnauthorisedException;
import com.chocksaway.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Author milesd on 17/12/2016.
 */

@Controller
public class AccountController {
    @Autowired
    private AccountRepository repository;

    @PostMapping("/authenticate")
    public ModelAndView authenticate(@RequestBody Account accountIn) {
        Optional<Account> user = Optional.ofNullable(repository.findByUsername(accountIn.getUsername()));

        if (!user.isPresent()) {
            throw new UserNotFoundException(accountIn.getUsername());
        }

        if (user.get().getPassword().equals(accountIn.getPassword())) {
            ModelAndView mav = new ModelAndView("authenticate");
            mav.addObject("account", repository.findByUsername(accountIn.getUsername()));

            return mav;

        }

        throw new UserUnauthorisedException(accountIn.getUsername());
    }


    @PostMapping("/register")
    public String greetingSubmit(@RequestBody Account accountIn) {
        Optional<Account> account = Optional.ofNullable(repository.findByUsername(accountIn.getUsername()));

        if (account == null) {
            repository.save(accountIn);
            return "result";
        }

        throw new UserAlreadyExistsException(accountIn.getUsername());
    }

    @GetMapping("/register")
    public String greetingForm(Model model) {
        return "register";
    }


    @GetMapping("/login")
    public String loginPut(Model model) {
        return "login";
    }


    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Account account) {
        return "home";
    }


    @GetMapping("/phrase/{id}")
    public String getPhrase(@RequestParam("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + 8090 + "/names")
                .queryParam("email", id);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        return "phrase";
    }
}

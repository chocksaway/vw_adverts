package com.chocksaway.controller;

import com.chocksaway.entity.Account;
import com.chocksaway.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Author milesd on 17/12/2016.
 */

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/register")
    public String greetingForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String greetingSubmit(@ModelAttribute Account account, Model model) {
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount == null) {
            accountRepository.save(account);
            return "result";
        }

        model.addAttribute("registerError", true);
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

    @GetMapping("/phrase")
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

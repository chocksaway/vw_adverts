package com.chocksaway.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Author milesd on 22/04/2016.
 */

public class Name  {
    @Id
    private String id;
    private String email;
    private String name;

    private List<Phrase> phrases;

    protected Name() {}

    public Name(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getId() + " " + getEmail() + "," + getName();
    }

    public List<Phrase> getPhraseList() {
        return phrases;
    }

    private void createPhrases() {
        phrases = new ArrayList<Phrase>();
    }

    public void setPhrase(Phrase phrase) {
        createPhrases();
        phrases.add(phrase);
    }
}

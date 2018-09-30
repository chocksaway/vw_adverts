package com.chocksaway.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author milesd on 20/09/2016.
 */
public class Phrase {
    private String phrase;
    private List<Site> sites;

    public Phrase(String phrase) {
        this.phrase = phrase;
    }
    
    private void createSites() {;
        sites = new ArrayList<>();
    }

    @Override
    public String toString() {
        return getPhrase() + "," + getSite();
    }

    public String getPhrase() {
        return phrase;
    }

    public List<Site> getSite() {
        return sites;
    }

    public void setSite(Site site) {
        if (sites == null) {
            createSites();
        }

        sites.add(site);
    }
}

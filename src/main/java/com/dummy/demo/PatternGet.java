package com.dummy.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PatternGet {
    @JsonProperty
    private String joke;

    @JsonProperty
    private LocalDate date;


    // Геттеры и сеттеры
    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
